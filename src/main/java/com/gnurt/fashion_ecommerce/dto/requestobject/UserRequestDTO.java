package com.gnurt.fashion_ecommerce.dto.requestobject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
	private String username;
	private String password;
	private String email;
	private String phone;
	private String address;
	private String role;
	private String avatar;
	private String fullname;
}
