package com.example.medicalmanagement.controller;

import com.example.medicalmanagement.dto.ApiResponse;
import com.example.medicalmanagement.entity.Supply;
import com.example.medicalmanagement.repository.SupplyRepository;
import com.example.medicalmanagement.service.SupplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/supplies")
@RequiredArgsConstructor
@Slf4j
public class SupplyController {

    private final SupplyService supplyService;
    private final SupplyRepository supplyRepo;

    // 1. Thêm mới vật tư y tế
    @PostMapping
    public ResponseEntity<ApiResponse<Supply>> create(@RequestBody Supply supply) {
        if (supply.getName() == null || supply.getName().isBlank()) {
            return ResponseEntity.badRequest().body(ApiResponse.<Supply>builder()
                    .status(400).message("Tên vật tư không được để trống").build());
        }
        supply.setQuantity(0); // Bất kể client gửi gì, mặc định = 0
        supply.setIsDeleted(false);
        Supply saved = supplyRepo.save(supply);

        log.info("Đã tạo mới vật tư: [{}] với ID: [{}]", saved.getName(), saved.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.<Supply>builder()
                .status(201).data(saved).build());
    }

    // 2. Cập nhật thông tin vật tư
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Supply>> update(@PathVariable Long id, @RequestBody Map<String, Object> body) {
        if (body.containsKey("id") || body.containsKey("quantity")) {
            log.warn("WARN: Client cố tình gửi dữ liệu cấm (id, quantity) cho ID {}", id);
            return ResponseEntity.badRequest().body(ApiResponse.<Supply>builder()
                    .status(400).message("Không được phép cập nhật id hoặc quantity").build());
        }

        Supply existing = supplyRepo.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy vật tư"));

        if (body.get("name") != null) existing.setName((String) body.get("name"));
        if (body.get("specification") != null) existing.setSpecification((String) body.get("specification"));
        if (body.get("provider") != null) existing.setProvider((String) body.get("provider"));

        Supply updated = supplyRepo.save(existing);
        return ResponseEntity.ok(ApiResponse.<Supply>builder().status(200).data(updated).build());
    }

    // 3. Xóa vật tư (Xóa mềm)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Supply s = supplyRepo.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("404")); // Sẽ được GlobalException xử lý trả 404
        s.setIsDeleted(true);
        supplyRepo.save(s);
        return ResponseEntity.noContent().build();
    }

    // 4. Hiển thị danh sách vật tư hiện có
    @GetMapping
    public ResponseEntity<ApiResponse<List<Supply>>> getAll() {
        List<Supply> list = supplyRepo.findAllByIsDeletedFalse();
        log.debug("Số lượng bản ghi vừa truy vấn được từ Database: {}", list.size());
        return ResponseEntity.ok(ApiResponse.<List<Supply>>builder()
                .status(200).data(list).build());
    }

    // 5. Tìm kiếm vật tư theo tên (Tìm kiếm tương đối)
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Supply>>> search(@RequestParam("name") String name) {
        List<Supply> list = supplyRepo.findByNameContainingIgnoreCaseAndIsDeletedFalse(name);
        if (list.isEmpty()) {
            log.info("Không tìm thấy vật tư nào khớp với từ khóa: [{}]", name);
        }
        return ResponseEntity.ok(ApiResponse.<List<Supply>>builder()
                .status(200).data(list).build());
    }

    // 6. Xuất vật tư y tế (Xuất kho)
    @PatchMapping("/{id}/export")
    public ResponseEntity<ApiResponse<Void>> export(@PathVariable Long id, @RequestBody Map<String, Integer> body) {
        Integer amount = body.get("amount");
        try {
            supplyService.exportSupply(id, amount);
            return ResponseEntity.ok(ApiResponse.<Void>builder().status(200).message("Xuất kho thành công").build());
        } catch (RuntimeException e) {
            // e.getMessage() ở đây chứa số lượng tồn hiện có (được ném ra từ Service)
            log.error("Thất bại khi xuất kho ID [{}]: Yêu cầu [{}], hiện có [{}]", id, amount, e.getMessage());
            return ResponseEntity.badRequest().body(ApiResponse.<Void>builder()
                    .status(400).message("Số lượng tồn kho không đủ để xuất").build());
        }
    }

    // 7. Nhập vật tư y tế (Nhập kho)
    @PatchMapping("/{id}/import")
    public ResponseEntity<ApiResponse<Void>> importSupply(@PathVariable Long id, @RequestBody Map<String, Integer> body) {
        Integer amount = body.get("amount");
        supplyService.importSupply(id, amount);
        return ResponseEntity.ok(ApiResponse.<Void>builder().status(200).message("Nhập kho thành công").build());
    }

    // 8. Thống kê vật tư đã xuất kho trong ngày
    @GetMapping("/statistics/daily-export")
    public ResponseEntity<ApiResponse<List<?>>> getDailyExport() {
        log.info("Bắt đầu chạy thống kê xuất kho trong ngày: {}", LocalDateTime.now());
        List<?> result = supplyService.getDailyStatistics();
        log.info("Hoàn thành thống kê lúc: {}", LocalDateTime.now());
        return ResponseEntity.ok(ApiResponse.<List<?>>builder().status(200).data(result).build());
    }

    // 9. Thống kê vật tư xuất kho nhiều nhất
    @GetMapping("/statistics/top-export")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getTopExport() {
        Map<String, Object> result = supplyService.getTopExportStatistics();
        if (result.isEmpty()) {
            return ResponseEntity.status(404).body(ApiResponse.<Map<String, Object>>builder()
                    .status(404).message("Chưa có dữ liệu giao dịch để thống kê").build());
        }
        return ResponseEntity.ok(ApiResponse.<Map<String, Object>>builder().status(200).data(result).build());
    }
}