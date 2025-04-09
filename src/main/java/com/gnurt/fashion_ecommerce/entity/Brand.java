package com.gnurt.fashion_ecommerce.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_brand")
@Getter
@Setter
public class Brand {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "brand_id")
	private Long brandId;
	@Column(name = "brand_name")
	private String brandName;
	@Column(name = "brand_detail")
	private String brandDetail;
	@Column(name = "brand_slug")
	private String brandSlug;

	@Column(name = "brand_status")
	private Boolean brandStatus;
	@Column(name = "brand_created_date")
	private LocalDate brandCreatedDate;
	@Column(name = "brand_updated_date")
	private LocalDate brandUpdatedDate;
	@Column(name = "brand_created_by")
	private Long brandCreatedBy;
	@Column(name = "brand_updated_by")
	private Long brandUpdatedBy;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "brand")
	@JsonManagedReference
	private List<Product> lstProduct;
}
