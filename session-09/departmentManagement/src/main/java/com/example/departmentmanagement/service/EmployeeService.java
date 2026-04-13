package com.example.departmentmanagement.service;

import com.example.departmentmanagement.dto.EmployeeCreateDTO;
import com.example.departmentmanagement.entity.Department;
import com.example.departmentmanagement.entity.Employee;
import com.example.departmentmanagement.exception.DuplicateResourceException;
import com.example.departmentmanagement.exception.FileStorageException;
import com.example.departmentmanagement.exception.ResourceNotFoundException;
import com.example.departmentmanagement.repository.DepartmentRepository;
import com.example.departmentmanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;
    private final Path root = Paths.get("uploads");

    public String uploadAvatar(Long id, MultipartFile file) {
        // 1. Tìm nhân viên
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy nhân viên ID: " + id));

        // 2. Validate file
        validateFile(file);

        try {
            // Tạo thư mục uploads nếu chưa có
            if (!Files.exists(root)) {
                Files.createDirectories(root);
            }

            // Đổi tên file để tránh trùng (Dùng UUID)
            String extension = getFileExtension(file.getOriginalFilename());
            String fileName = UUID.randomUUID().toString() + extension;

            // Lưu file vào thư mục
            Files.copy(file.getInputStream(), this.root.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);

            // Cập nhật URL vào DB (Ở đây lưu tên file hoặc path)
            employee.setAvatarUrl(fileName);
            employeeRepository.save(employee);

            return fileName;
        } catch (IOException e) {
            throw new FileStorageException("Không thể lưu file: " + e.getMessage());
        }
    }

    private void validateFile(MultipartFile file) {
        // Kiểm tra trống
        if (file.isEmpty()) {
            throw new FileStorageException("Vui lòng chọn một file để upload");
        }

        // Kiểm tra kích thước (< 2MB)
        if (file.getSize() > 2 * 1024 * 1024) {
            throw new FileStorageException("File quá lớn! Vui lòng upload file dưới 2MB");
        }

        // Kiểm tra định dạng
        List<String> allowedExtensions = Arrays.asList(".jpg", ".jpeg", ".png");
        String fileName = file.getOriginalFilename();
        boolean isValid = allowedExtensions.stream()
                .anyMatch(ext -> fileName != null && fileName.toLowerCase().endsWith(ext));

        if (!isValid) {
            throw new FileStorageException("Định dạng file không hợp lệ! Chỉ chấp nhận .jpg, .jpeg, .png");
        }
    }

    private String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }
    public Employee createEmployee(EmployeeCreateDTO dto) {
        Department dept = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Phòng ban không tồn tại với ID: " + dto.getDepartmentId()));

        if (employeeRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateResourceException("Email đã được sử dụng: " + dto.getEmail());
        }

        Employee employee = new Employee();
        employee.setFullName(dto.getFullName());
        employee.setEmail(dto.getEmail());
        employee.setPhone(dto.getPhone());
        employee.setSalary(dto.getSalary());
        employee.setDepartment(dept);

        return employeeRepository.save(employee);
    }
}