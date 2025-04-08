package com.gnurt.fashion_ecommerce.dto.requestobject;

import lombok.Data;

@Data
public class ChangePasswordDTO {
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;
}
