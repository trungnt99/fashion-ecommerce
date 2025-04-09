package com.gnurt.fashion_ecommerce.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gnurt.fashion_ecommerce.exception.token.TokenExpirationException;
import com.gnurt.fashion_ecommerce.exception.token.TokenInvalidException;
import com.gnurt.fashion_ecommerce.service.JWTTokenService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JWTTokenServiceImpl implements JWTTokenService {

	@Value("${application.token.key}")
	private String key;
	@Value("${application.token.expiration_access_token}")
	private long expireTimeAccessToken;
	@Value("${application.token.expiration_refresh_token}")
	public long expireTimeRefreshToken;

	@Override
	public String generateAccessToken(Long userId, Map<String, Object> claims) {
		log.info("generateAccessToken start");
		return generateToken(userId, claims, expireTimeAccessToken);
	}

	@Override
	public String generateRefreshToken(Long userId, String username) {
		log.info("generateRefreshToken start");
		var claims = new HashMap<String, Object>();
		claims.put("username", username);
		return generateToken(userId, claims, expireTimeRefreshToken);
	}

	@Override
	public String getSubjectFromToken(String token) {
		log.info("getSubjectFromToken start");
		validateToken(token);
		return getClaims(token, key).getSubject();
	}

	@Override
	public Long getExpirationTime(String token) {
		log.info("getExpirationTime start");
		Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(token);
		Claims claims = claimsJws.getBody();
		return claims.getExpiration().getTime();
	}

	@Override
	public String getUsernameFromToken(String token) {
		validateToken(token);
		log.info("getUsernameFromToken start");
		@SuppressWarnings("unchecked")
		Map<String, Object> claims = (Map<String, Object>) getClaims(token, key).get("claims");
		return String.valueOf(claims.get("username"));
	}

	/**
	 * Generate token
	 * 
	 * @param userId
	 * @param claims
	 * @param tokenLifeTime
	 * @return
	 */
	private String generateToken(Long userId, Map<String, Object> claims, long tokenLifeTime) {
		log.info("generateToken start");
		return Jwts.builder().setSubject(String.valueOf(userId)).claim("claims", claims)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + tokenLifeTime))
				.signWith(SignatureAlgorithm.HS256, key).compact();
	}

	/**
	 * Lấy thông tin ở trong token
	 * 
	 * @param token
	 * @param secretKey
	 * @return
	 */
	private Claims getClaims(String token, String secretKey) {
		log.info("getClaims start");
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
	}

	/**
	 * Check token
	 * 
	 * @param token
	 */
	private void validateToken(String token) {
		log.info("validateToken start");
		if (!isValidToken(token)) {
			log.error("TokenInvalidException");
			throw new TokenInvalidException();
		}
		if (!isExpiredToken(token)) {
			log.error("TokenExpiredException");
			throw new TokenExpirationException();
		}
	}

	/**
	 * Kiểm tra xem token đã hết hạn hay chưa?
	 * 
	 * @param token
	 * @return
	 */
	private Boolean isExpiredToken(String token) {
		return (Boolean) getClaims(token, key).getExpiration().before(new Date());
	}

	/**
	 * Kiểm tra token có hợp lệ hay không?
	 * 
	 * @param token
	 * @return
	 */
	private Boolean isValidToken(String token) {
		try {
			Jwts.parser().setSigningKey(key).parseClaimsJws(token);
			return (Boolean) true;
		} catch (JwtException | IllegalArgumentException e) {
			return (Boolean) false;
		}
	}

	@Override
	public Boolean validateToken(String token, String username) {
		log.info("validateToken start");
		String tokenUsername = getUsernameFromToken(token);
		return (username.equals(tokenUsername) && !isExpiredToken(token));

	}

}
