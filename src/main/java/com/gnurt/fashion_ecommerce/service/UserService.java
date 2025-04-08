package com.gnurt.fashion_ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.gnurt.fashion_ecommerce.dto.requestobject.ChangePasswordDTO;
import com.gnurt.fashion_ecommerce.dto.requestobject.UserRequestDTO;
import com.gnurt.fashion_ecommerce.dto.responseobject.UserResponseDTO;
import com.gnurt.fashion_ecommerce.entity.User;

public interface UserService {
	UserResponseDTO createUser(UserRequestDTO request);

	UserResponseDTO updateUser(UserRequestDTO request, Long id);

	void deleteUserById(Long id);

	UserResponseDTO getUserById(Long id);

	List<UserResponseDTO> getListUser(String keyword, int size, int page, boolean isActived);

	void changePassword(Long id, ChangePasswordDTO request);

	void changeActive(Long id);

	void equalPassword(String passwordRaw, String passwordEncrypted);

	Optional<User> getByUsername(String username);
}
