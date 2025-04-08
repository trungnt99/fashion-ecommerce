package com.gnurt.fashion_ecommerce.exception.token;

import com.gnurt.fashion_ecommerce.exception.base.BaseException;

public class TokenExpirationException extends BaseException {
	public TokenExpirationException() {
		setCode("com.gnurt.fashion_ecommerce.exception.token.TokenExpirationException");
	}

}
