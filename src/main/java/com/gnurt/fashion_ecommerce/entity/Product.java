package com.gnurt.fashion_ecommerce.entity;

import java.time.LocalDate;
import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long productId;
	@Column(name = "product_name")
	private String productName;
	@Column(name = "product_description")
	private String productDescription;
	@Column(name = "product_price")
	private Double productPrice;
	@Column(name = "product_discount")
	private Double productDiscount;
	@Column(name = "product_stock")
	private Integer productStock;
	@Column(name = "product_year")
	private Integer productYear;
	@Column(name = "product_slug")
	private String productSlug;
	@Column(name = "product_image")
	private String productImage;
	@Column(name = "product_sold")
	private Integer productSold;

	@Column(name = "product_status")
	private Boolean productStatus;
	@Column(name = "product_created_date")
	private LocalDate productCreatedDate;
	@Column(name = "product_updated_date")
	private LocalDate productUpdatedDate;
	@Column(name = "product_created_by")
	private Long productCreatedBy;
	@Column(name = "product_updated_by")
	private Long productUpdatedBy;

	@Column(name = "brand_id", insertable = false, updatable = false)
	private Long brandId;
	@ManyToOne
	@JoinColumn(name = "brand_id")
	@JsonBackReference
	private Brand brand;
	@Column(name = "category_id", insertable = false, updatable = false)
	private Long categoryId;
	@ManyToOne
	@JoinColumn(name = "category_id")
	@JsonBackReference
	private Category category;

	@Transient
	private boolean isNew;

	public boolean getIsNew() {
		return Calendar.getInstance().get(Calendar.YEAR) == productYear;
	}
}
