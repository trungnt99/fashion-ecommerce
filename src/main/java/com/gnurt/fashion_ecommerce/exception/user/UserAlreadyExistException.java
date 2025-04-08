package com.gnurt.fashion_ecommerce.exception.user;

import com.gnurt.fashion_ecommerce.exception.base.ConflictException;

public class UserAlreadyExistException extends ConflictException {
	public UserAlreadyExistException() {
		setMessage("com.gnurt.fashion_ecommerce.exception.user.UserAlreadyExistException");
	}

}
