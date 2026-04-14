package com.example.clinicmanagement.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/patients")
@Slf4j
public class PatientController {

    @PostMapping
    public String addPatient(@RequestBody Map<String, Object> patientData) {
        String name = (String) patientData.get("name");
        int age = (int) patientData.get("age");

        // Yêu cầu 1: Ghi log INFO hiển thị tên bệnh nhân
        log.info("Tiếp nhận yêu cầu thêm mới bệnh nhân: {}", name);

        // Yêu cầu 2: Ghi log WARN nếu tuổi > 120
        if (age > 120) {
            log.warn("Cảnh báo: Bệnh nhân {} có số tuổi bất thường: {} tuổi", name, age);
        }

        return "Bệnh nhân " + name + " đã được xử lý!";
    }
}