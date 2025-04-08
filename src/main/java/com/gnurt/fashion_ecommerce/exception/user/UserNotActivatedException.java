package com.gnurt.fashion_ecommerce.exception.user;

import com.gnurt.fashion_ecommerce.exception.base.ConflictException;

public class UserNotActivatedException extends ConflictException {
	public UserNotActivatedException() {
		setMessage("com.gnurt.fashion_ecommerce.exception.user.UserNotActivatedException");
	}
}
