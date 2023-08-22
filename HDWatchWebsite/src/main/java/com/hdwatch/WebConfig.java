package com.hdwatch;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// Đăng ký một ResourceHandler để xử lý các tệp hình ảnh (images)
		// Đường dẫn /images/** là đường dẫn mà người dùng sẽ truy cập để lấy tệp hình ảnh
		// Thư mục "classpath:/static/images/" chứa các tệp hình ảnh trong ứng dụng
		registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/");
	}
}
