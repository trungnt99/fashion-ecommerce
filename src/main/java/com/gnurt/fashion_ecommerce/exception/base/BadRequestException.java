package com.gnurt.fashion_ecommerce.exception.base;

public class BadRequestException extends BaseException {
	public BadRequestException() {
		setCode("com.gnurt.fashion_ecommerce.exception.base.BadRequestException");
		setStatus(400);
	}
}
