package com.gnurt.fashion_ecommerce.exception.user;

import com.gnurt.fashion_ecommerce.exception.base.ConflictException;

public class EmailAlreadyExistException extends ConflictException {
	public EmailAlreadyExistException() {
		setMessage("com.gnurt.fashion_ecommerce.exception.user.EmailAlreadyExistException");
	}

}