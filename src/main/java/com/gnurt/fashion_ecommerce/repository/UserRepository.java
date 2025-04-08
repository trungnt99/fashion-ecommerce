package com.gnurt.fashion_ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gnurt.fashion_ecommerce.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Boolean existsByEmail(String email);

	Boolean existsByUsername(String username);

	Optional<User> findByUsername(String username);

	@Modifying
	@Query(value = "update tbl_user set status = !status where user_id = :id", nativeQuery = true)
	void changeActive(Long id);
}
