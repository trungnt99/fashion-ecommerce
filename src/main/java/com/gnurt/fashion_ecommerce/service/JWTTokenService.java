package com.gnurt.fashion_ecommerce.service;

import java.util.Map;

public interface JWTTokenService {
	String generateAccessToken(Long userId, Map<String, Object> claims);

	String generateRefreshToken(Long userId, String username);

	String getSubjectFromToken(String token);

	Long getExpirationTime(String token);

	String getUsernameFromToken(String token);
}
