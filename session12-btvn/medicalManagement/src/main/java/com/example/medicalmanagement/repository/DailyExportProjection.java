package com.example.medicalmanagement.repository;

public interface DailyExportProjection {
    Long getSupplyId();
    String getSupplyName();
    Long getTotalExported();
}