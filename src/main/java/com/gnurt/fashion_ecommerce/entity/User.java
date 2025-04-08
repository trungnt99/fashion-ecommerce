package com.gnurt.fashion_ecommerce.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_user")
@Getter
@Setter
public class User {
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "fullname")
	private String fullname;
	@Column(name = "sdt")
	private String sdt;
	@Column(name = "email")
	private String email;
	@Column(name = "dob")
	private LocalDate dob;
	@Column(name = "detail_address")
	private String detailAddress;
	@Column(name = "refresh_token")
	private String refreshToken;
	@Column(name = "refresh_token_expired")
	private LocalDate refreshTokenExpired;
	@Column(name = "access_token")
	private String accessToken;
	@Column(name = "access_token_expired")
	private LocalDate accessTokenExpired;
	@Column(name = "status")
	private Boolean status;
	@Column(name = "role_id", insertable = false, updatable = false)
	private Long roleId;
	@ManyToOne
	@JoinColumn(name = "role_id")
	@JsonBackReference
	private Role role;
	@Column(name = "created_date")
	private LocalDate createdDate;
	@Column(name = "updated_date")
	private LocalDate updatedDate;
}
