package com.gnurt.fashion_ecommerce.exception.base;

public class ConflictException extends BaseException {
	public ConflictException() {
		setStatus(409);
		setCode("com.gnurt.todolist.demo.exception.base.ConflictException");
	}

	public ConflictException(String id, String objectName) {
		setStatus(409);
		setCode("com.gnurt.todolist.demo.exception.base.ConflictException");
		addParam("id", id);
		addParam("objectName", objectName);
	}
}
