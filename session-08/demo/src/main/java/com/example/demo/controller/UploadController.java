package com.example.demo.controller;

import com.example.demo.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/uploads")
@RequiredArgsConstructor
public class UploadController {
    private final UploadService uploadService;
    // Client gui file, nhan file upload vao o dia/cloud -> tra ve duong dan
    // De nhan file: content-type: form data
    // method: post, put, patch
    // doi tuong dai dien cho 1 file: MultiPartFile
    @PostMapping
    public ResponseEntity<?> upload(@RequestParam MultipartFile file) throws IOException {
//        String linkFolder ="C";
//        String fileName = UUID.randomUUID()+"_"+file.getOriginalFilename(); // ten file
//        file.transferTo(new File(linkFolder+File.separator+fileName));
        String url = uploadService.uploadFile(file);
        return ResponseEntity.ok().body("OK");
    }
}
