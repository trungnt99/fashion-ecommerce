package com.gnurt.fashion_ecommerce.service;

import com.gnurt.fashion_ecommerce.dto.responseobject.LoginResponseDTO;

public interface AuthService {
	LoginResponseDTO login(String username, String password);

	void logout();

	String refreshToken(String token);
}
