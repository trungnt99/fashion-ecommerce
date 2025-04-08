package com.gnurt.fashion_ecommerce.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.gnurt.fashion_ecommerce.exception.token.TokenExpirationException;
import com.gnurt.fashion_ecommerce.exception.token.TokenInvalidException;
import com.gnurt.fashion_ecommerce.service.JWTTokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class SecurityConfig extends OncePerRequestFilter{

	private final JWTTokenService jwtTokenService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
			 FilterChain filterChain)
			throws ServletException, IOException {
		log.debug("(doFilterInternal)request: {}, response: {}, filterChain: {}", request, response, filterChain);

		String accessToken = request.getHeader("Authorization");

		if (Objects.isNull(accessToken)) {
			filterChain.doFilter(request, response);
			return;
		} else if (!accessToken.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}

		var jwtToken = accessToken.substring("Bearer ".length());
		try {
			var userid = jwtTokenService.getSubjectFromToken(jwtToken);
			var username = jwtTokenService.getUsernameFromToken(jwtToken);

			var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userid, username,
					new ArrayList<>());
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			filterChain.doFilter(request, response);
		} catch (TokenInvalidException e) {
			response.sendError(HttpStatus.UNAUTHORIZED.value(), "Token is invalid");
		} catch (TokenExpirationException e) {
			response.sendError(HttpStatus.UNAUTHORIZED.value(), "Token is expired");
		}

		filterChain.doFilter(request, response);

	}
}
