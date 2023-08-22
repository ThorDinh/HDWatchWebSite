package com.hdwatch.api;

import java.io.File;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hdwatch.service.UploadService;

@CrossOrigin("*")
@RestController
public class UploadRestController {
	@Autowired
	UploadService uploadService;
	
	// Xử lý yêu cầu POST để tải lên tập tin
	@PostMapping("/rest/upload/{folder}")
	public JsonNode upload(@PathParam("file") MultipartFile file, @PathVariable("folder") String folder) {
	    // Lưu trữ tập tin và nhận về tập tin đã lưu
	    File savedFile = uploadService.save(file, folder);

	    // Khởi tạo đối tượng ObjectMapper để chuyển đổi dữ liệu thành JSON
	    ObjectMapper mapper = new ObjectMapper();

	    // Tạo một đối tượng ObjectNode để chứa thông tin tập tin đã lưu
	    ObjectNode node = mapper.createObjectNode();
	    node.put("name", savedFile.getName());  // Thêm thông tin tên tập tin
	    node.put("size", savedFile.length());   // Thêm thông tin kích thước tập tin

	    return node;  // Trả về đối tượng JSON chứa thông tin về tập tin
	}

}