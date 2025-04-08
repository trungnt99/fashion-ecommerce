package com.gnurt.fashion_ecommerce.dto.requestobject;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDTO {
	@NotBlank
	private String username;
	@NotBlank
	@Min(6)
	private String password;
}
