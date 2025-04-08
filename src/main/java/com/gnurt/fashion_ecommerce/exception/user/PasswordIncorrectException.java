package com.gnurt.fashion_ecommerce.exception.user;

import com.gnurt.fashion_ecommerce.exception.base.ConflictException;

public class PasswordIncorrectException extends ConflictException {
	public PasswordIncorrectException() {
		setMessage("com.gnurt.fashion_ecommerce.exception.user.PasswordIncorrectException");
	}
}
