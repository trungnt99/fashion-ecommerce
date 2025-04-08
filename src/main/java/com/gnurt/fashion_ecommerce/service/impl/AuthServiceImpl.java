package com.gnurt.fashion_ecommerce.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.gnurt.fashion_ecommerce.dto.responseobject.LoginResponseDTO;
import com.gnurt.fashion_ecommerce.entity.User;
import com.gnurt.fashion_ecommerce.exception.user.UserNotActivatedException;
import com.gnurt.fashion_ecommerce.service.AuthService;
import com.gnurt.fashion_ecommerce.service.JWTTokenService;
import com.gnurt.fashion_ecommerce.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
	@Autowired
	private UserService iUserService;

	@Autowired
	private JWTTokenService jwtTokenService;

	@Override
	public LoginResponseDTO login(String username, String password) {
		log.info("login start");
		User user = iUserService.getByUsername(username).get();
		if (!user.getStatus()) {
			throw new UserNotActivatedException();
		}
		iUserService.equalPassword(password, user.getPassword());
		var claims = new HashMap<String, Object>();
		claims.put("username", user.getUsername());
		final String accessToken = jwtTokenService.generateAccessToken(user.getUserId(), claims);

		final String refreshToken = jwtTokenService.generateRefreshToken(user.getUserId(), user.getUsername());
		return new LoginResponseDTO(accessToken, refreshToken);
	}

	@Override
	public void logout() {
		log.info("logout start");
		SecurityContextHolder.clearContext();
	}

	@Override
	public String refreshToken(String token) {
		return null;
	}
}
