package com.gnurt.fashion_ecommerce.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gnurt.fashion_ecommerce.dto.requestobject.ChangePasswordDTO;
import com.gnurt.fashion_ecommerce.dto.requestobject.UserRequestDTO;
import com.gnurt.fashion_ecommerce.dto.responseobject.UserResponseDTO;
import com.gnurt.fashion_ecommerce.entity.User;
import com.gnurt.fashion_ecommerce.exception.user.EmailAlreadyExistException;
import com.gnurt.fashion_ecommerce.exception.user.PasswordIncorrectException;
import com.gnurt.fashion_ecommerce.exception.user.UserAlreadyExistException;
import com.gnurt.fashion_ecommerce.exception.user.UserNotFoundException;
import com.gnurt.fashion_ecommerce.repository.UserRepository;
import com.gnurt.fashion_ecommerce.service.UserService;
import com.gnurt.fashion_ecommerce.utils.MapperUtils;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public UserResponseDTO createUser(UserRequestDTO request) {
		log.info("createUser start");
		checkExistedPreCreate(request.getUsername(), request.getEmail());
		User user = MapperUtils.toEntity(request, User.class);
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setStatus(true);
		user.setCreatedDate(LocalDate.now());
		user.setUpdatedDate(LocalDate.now());

		return MapperUtils.toModel(userRepository.save(user), UserResponseDTO.class);
	}

	@Override
	@Transactional
	public UserResponseDTO updateUser(UserRequestDTO request, Long id) {
		log.info("updateUser start");
		User user = userRepository.findById(id).orElseThrow();
		checkExistedPreUpdate(user, request.getEmail());
		MapperUtils.MODEL_MAPPER.map(request, user);
		return MapperUtils.toModel(userRepository.save(user), UserResponseDTO.class);
	}

	@Override
	@Transactional
	public void deleteUserById(Long id) {
		log.info("deleteUserById start");
		User user = userRepository.findById(id).orElseThrow();
		if (user.getRole().getRoleCode().equals("ADMIN")) {
			throw new UnsupportedOperationException("Cannot delete admin");
		}
		userRepository.deleteById(id);
	}

	@Override
	@Transactional
	public UserResponseDTO getUserById(Long id) {
		log.info("getUserById start");
		return MapperUtils.toModel(userRepository.findById(id).orElseThrow(UserNotFoundException::new),
				UserResponseDTO.class);
	}

	@Override
	public List<UserResponseDTO> getListUser(String keyword, int size, int page, boolean isActived) {

		return null;
	}

	@Override
	@Transactional
	public void changeActive(Long id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty()) {
			throw new UserNotFoundException();
		}
		userRepository.changeActive(id);
	}

	@Override
	public void equalPassword(String passwordRaw, String passwordEncrypted) {
		if (!passwordEncoder.matches(passwordRaw, passwordEncrypted)) {
			throw new PasswordIncorrectException();
		}

	}

	@Override
	public Optional<User> getByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public void changePassword(Long id, ChangePasswordDTO request) {
		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty()) {
			throw new UserNotFoundException();
		}
		if (request.getNewPassword().equals(request.getConfirmPassword())) {
			throw new PasswordIncorrectException();
		}
		equalPassword(request.getOldPassword(), user.get().getPassword());
		user.get().setPassword(passwordEncoder.encode(request.getNewPassword()));
		user.get().setUpdatedDate(LocalDate.now());
		userRepository.save(user.get());
	}

	/**
	 * Check existed email
	 * 
	 * @param email
	 */
	private void checkExistedEmail(String email) {
		log.info("checkExistedEmail start: email = {}", email);
		if (userRepository.existsByEmail(email)) {
			log.error("Email is existed");
			throw new EmailAlreadyExistException();
		}
	}

	/**
	 * Check existed username
	 * 
	 * @param username
	 */
	private void checkExistedUsername(String username) {
		log.info("checkExistedUsername start: username = {}", username);
		if (userRepository.existsByUsername(username)) {
			log.error("Username is existed");
			throw new UserAlreadyExistException();
		}
	}

	/**
	 * Check existed pre create
	 * 
	 * @param username
	 * @param email
	 */
	private void checkExistedPreCreate(String username, String email) {
		log.info("checkExistedPreCreate start: username = {}, email = {}", username, email);
		checkExistedUsername(username);
		checkExistedEmail(email);
	}

	private void checkExistedPreUpdate(User user, String email) {
		log.info("checkExistedPreUpdate start: user = {}, email = {}", user, email);
		if (!user.getEmail().equals(email)) {
			checkExistedEmail(email);
		}
	}

}
