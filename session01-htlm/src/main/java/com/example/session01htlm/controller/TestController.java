package com.example.session01htlm.controller;

import com.example.session01htlm.model.RequestModel;
import com.example.session01htlm.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {
    @PostMapping("/test/sum")
    public int sum(@RequestBody RequestModel model){
        return model.getA()+model.getB();
    }
    @GetMapping("/students/list")
    public List<Student> getListStudent() {
        // Tạo một danh sách sinh viên giả lập
        List<Student> list = new ArrayList<>();

        // Nhờ có @AllArgsConstructor từ Lombok, bạn có thể khởi tạo nhanh như thế này
        list.add(new Student("Quân Nguyễn", 20));
        list.add(new Student("Thanh Tùng", 21));
        list.add(new Student("Minh Anh", 19));

        return list;
        // Khi chạy, Spring sẽ trả về JSON dạng: [{"name":"Quân Nguyễn","age":20}, ...]
    }
    @GetMapping("/students/listMap")
    public Map<String, Object> getListMapStudent() {
        // 1. Tạo danh sách sinh viên như cũ
        List<Student> list = new ArrayList<>();
        list.add(new Student("Quân Nguyễn", 20));
        list.add(new Student("Thanh Tùng", 21));
        list.add(new Student("Minh Anh", 19));

        // 2. Tạo Map để đóng gói dữ liệu
        Map<String, Object> response = new HashMap<>();

        // Thêm các thông tin bổ trợ (Metadata)
        response.put("status", "success");
        response.put("message", "Lấy danh sách thành công");
        response.put("total", list.size());

        // Thêm dữ liệu chính vào Map
        response.put("data", list);

        return response;
    }
}
