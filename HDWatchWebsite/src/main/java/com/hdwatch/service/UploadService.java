package com.hdwatch.service;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    
    // Lưu tập tin từ MultipartFile vào thư mục cụ thể
    File save(MultipartFile file, String folder);

}
