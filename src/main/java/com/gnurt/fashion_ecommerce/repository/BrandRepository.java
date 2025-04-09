package com.gnurt.fashion_ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gnurt.fashion_ecommerce.entity.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
	Brand findByBrandName(String brandName);
}
