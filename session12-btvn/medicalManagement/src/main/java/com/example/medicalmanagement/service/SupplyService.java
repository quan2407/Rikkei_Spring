package com.example.medicalmanagement.service;

import com.example.medicalmanagement.entity.Supply;
import com.example.medicalmanagement.entity.Transaction;
import com.example.medicalmanagement.repository.DailyExportProjection;
import com.example.medicalmanagement.repository.SupplyRepository;
import com.example.medicalmanagement.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SupplyService {
    private final SupplyRepository supplyRepo;
    private final TransactionRepository transRepo;
    private static final Logger historyLog = LoggerFactory.getLogger("HistoryLogger");

    @Transactional
    public void importSupply(Long id, int amount) {
        Supply s = supplyRepo.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy vật tư"));
        int oldQty = s.getQuantity();
        s.setQuantity(oldQty + amount);
        supplyRepo.save(s);

        transRepo.save(Transaction.builder().supplyId(id).amount(amount).type("IMPORT").createdAt(LocalDateTime.now()).build());

        // Log vào history.log (Chức năng 7)
        historyLog.info("Nhập kho ID {}, số lượng [{}], tồn cũ [{}]", id, amount, oldQty);
    }

    @Transactional
    public void exportSupply(Long id, int amount) {
        Supply s = supplyRepo.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy vật tư"));

        if (s.getQuantity() < amount) {
            throw new RuntimeException("Số lượng tồn kho không đủ để xuất");
        }

        s.setQuantity(s.getQuantity() - amount);
        supplyRepo.save(s);
        transRepo.save(Transaction.builder().supplyId(id).amount(amount).type("EXPORT").createdAt(LocalDateTime.now()).build());
    }

    // Chức năng 8
    public List<DailyExportProjection> getDailyStatistics() {
        LocalDateTime startOfDay = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        return transRepo.findDailyExportStatistics(startOfDay);
    }

    // Chức năng 9
    public Map<String, Object> getTopExportStatistics() {
        return transRepo.findTopExportRecord()
                .map(record -> {
                    Map<String, Object> map = new java.util.HashMap<>();
                    map.put("topSupplyName", record.getTopSupplyName());
                    map.put("totalExportQuantity", record.getTotalExportQuantity());
                    return map;
                })
                .orElse(java.util.Collections.emptyMap());
    }
}