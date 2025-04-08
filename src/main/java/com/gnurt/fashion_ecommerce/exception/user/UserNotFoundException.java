package com.gnurt.fashion_ecommerce.exception.user;

import com.gnurt.fashion_ecommerce.exception.base.ConflictException;

public class UserNotFoundException extends ConflictException {
	public UserNotFoundException() {
		setMessage("com.gnurt.fashion_ecommerce.exception.user.UserNotFoundException");
	}

}
