package com.example.medicalmanagement.repository;

import com.example.medicalmanagement.entity.Supply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplyRepository extends JpaRepository<Supply, Long> {

    /**
     * Chức năng 4: Lấy danh sách vật tư chưa bị xóa (isDeleted = false)
     */
    List<Supply> findAllByIsDeletedFalse();

    /**
     * Chức năng 3, 6, 7: Tìm kiếm vật tư cụ thể theo ID và phải chưa bị xóa
     */
    Optional<Supply> findByIdAndIsDeletedFalse(Long id);

    /**
     * Chức năng 5: Tìm kiếm tương đối theo tên (LIKE %name%)
     * và chỉ lấy các bản ghi chưa bị xóa
     */
    List<Supply> findByNameContainingIgnoreCaseAndIsDeletedFalse(String name);
}