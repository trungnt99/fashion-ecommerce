package com.gnurt.fashion_ecommerce.exception.base;

public class NotFoundException extends BaseException {
	public NotFoundException() {
		setStatus(404);
		setCode("com.gnurt.fashion_ecommerce.exception.base.NotFoundException");
	}

	public NotFoundException(String id, String objectName) {
		setStatus(404);
		setCode("com.gnurt.fashion_ecommerce.exception.base.NotFoundException");
		addParam("id", id);
		addParam("objectName", objectName);
	}

}
