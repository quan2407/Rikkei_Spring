package com.example.medicalmanagement.repository;

import com.example.medicalmanagement.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE t.type = 'EXPORT' AND t.createdAt >= ?1")
    List<Transaction> findAllExportsByDate(LocalDateTime date);

    @Query(value = "SELECT supply_id FROM transactions WHERE type = 'EXPORT' GROUP BY supply_id ORDER BY SUM(amount) DESC LIMIT 1", nativeQuery = true)
    Long findTopExportSupplyId();
    @Query(value = "SELECT t.supply_id as supplyId, s.name as supplyName, SUM(t.amount) as totalExported " +
            "FROM transactions t " +
            "JOIN supplies s ON t.supply_id = s.id " +
            "WHERE t.type = 'EXPORT' AND t.created_at >= :startOfDay " +
            "GROUP BY t.supply_id, s.name", nativeQuery = true)
    List<DailyExportProjection> findDailyExportStatistics(@Param("startOfDay") LocalDateTime startOfDay);

    // Chức năng 9: Tìm "quán quân" xuất kho
    @Query(value = "SELECT s.name as topSupplyName, SUM(t.amount) as totalExportQuantity " +
            "FROM transactions t " +
            "JOIN supplies s ON t.supply_id = s.id " +
            "WHERE t.type = 'EXPORT' " +
            "GROUP BY s.name " +
            "ORDER BY totalExportQuantity DESC " +
            "LIMIT 1", nativeQuery = true)
    Optional<TopExportProjection> findTopExportRecord();
}