package com.gnurt.fashion_ecommerce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**") // Apply to all endpoints
				.allowedOrigins("http://localhost:3000", "http://localhost:8080") // Allowed frontend origin
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allowed methods
				.allowedHeaders("*") // Allow all headers
				.allowCredentials(true); // Allow credentials
	}
}
