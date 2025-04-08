package com.gnurt.fashion_ecommerce.exception.base;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BaseException extends RuntimeException {
	private String code;
	private String message;
	private int status;
	private Map<String, String> params;

	public BaseException() {
		this.status = 0;
		this.code = "";
		this.message = "";
		this.params = new HashMap<>();
	}

	public void addParam(String key, String value) {
		if (Objects.isNull(params)) {
			this.params = new HashMap<>();
		}
		params.put(key, value);
	}
}
