package com.gnurt.fashion_ecommerce.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gnurt.fashion_ecommerce.entity.Brand;

public interface BrandService {

	Page<Brand> getAllBrands(Pageable pageable);

	/**
	 * Get all brands.
	 *
	 * @return a list of all brands
	 */
	List<Brand> getAllBrands();

	/**
	 * Get a brand by its ID.
	 *
	 * @param id the ID of the brand
	 * @return the brand with the specified ID
	 */
	Brand getBrandById(Long id);

	/**
	 * Create a new brand.
	 *
	 * @param brand the brand to create
	 * @return the created brand
	 */
	Brand createBrand(Brand brand);

	/**
	 * Update an existing brand.
	 *
	 * @param id    the ID of the brand to update
	 * @param brand the updated brand information
	 * @return the updated brand
	 */
	Brand updateBrand(Long id, Brand brand);

	/**
	 * Delete a brand by its ID.
	 *
	 * @param id the ID of the brand to delete
	 */
	void deleteBrand(Long id);
}
