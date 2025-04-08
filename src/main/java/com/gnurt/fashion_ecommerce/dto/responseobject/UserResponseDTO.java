package com.gnurt.fashion_ecommerce.dto.responseobject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {
	private Long id;
	private String username;
	private String password;
	private String email;
	private String phone;
	private String address;
	private String role;
	private String avatar;
	private String fullName;
}
