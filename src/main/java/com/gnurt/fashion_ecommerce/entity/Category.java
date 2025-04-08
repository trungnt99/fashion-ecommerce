package com.gnurt.fashion_ecommerce.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_category")
@Getter
@Setter
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private Long categoryId;
	@Column(name = "category_name")
	private String categoryName;
	@Column(name = "category_detail")
	private String categoryDetail;
	@Column(name = "category_slug")
	private String categorySlug;
	@Column(name = "category_image")
	private String categoryImage;
	@Column(name = "category_status")
	private Boolean categoryStatus;
	@Column(name = "category_created_date")
	private LocalDate categoryCreatedDate;
	@Column(name = "category_updated_date")
	private LocalDate categoryUpdatedDate;
	@Column(name = "category_created_by")
	private Long categoryCreatedBy;
	@Column(name = "category_updated_by")
	private Long categoryUpdatedBy;

}
