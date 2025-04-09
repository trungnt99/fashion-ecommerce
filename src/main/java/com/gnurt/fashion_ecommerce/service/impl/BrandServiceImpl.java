package com.gnurt.fashion_ecommerce.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.gnurt.fashion_ecommerce.entity.Brand;
import com.gnurt.fashion_ecommerce.repository.BrandRepository;
import com.gnurt.fashion_ecommerce.service.BrandService;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BrandServiceImpl implements BrandService {
	private RedisTemplate<String, Object> redisTemplate;
	// Inject the BrandRepository here
	private final BrandRepository brandRepository;

	public BrandServiceImpl(BrandRepository brandRepository) {
		this.brandRepository = brandRepository;
	}

	@Override
	public List<Brand> getAllBrands() {
		log.info("getAllBrands start");
		List<Brand> lstBrand = (List<Brand>) redisTemplate.opsForList().getLast("lstbrand");
		if (lstBrand != null) {
			return lstBrand;
		} else {
			// If not found in Redis, fetch from database
			lstBrand = brandRepository.findAll();
			// Store the result in Redis
			redisTemplate.opsForList().rightPush("lstbrand", lstBrand);
			return lstBrand;
		}

	}

	@Override
	public Brand getBrandById(Long id) {
		log.info("getBrandById start");
		Brand brand = new Brand();
		// Check if the brand exists in Redis
		List<Brand> lstBrand = (List<Brand>) redisTemplate.opsForList().getLast("lstbrand");
		if (lstBrand != null) {
			brand = lstBrand.stream().filter(b -> b.getBrandId().equals(id)).findFirst().orElse(null);
			if (brand != null) {
				return brand;
			}
		}
		// If not found in Redis, fetch from database
		brand = brandRepository.findById(id).orElseThrow(() -> new RuntimeException("Brand not found"));
		// Store the result in Redis
		if (lstBrand != null) {
			lstBrand.add(brand);
			redisTemplate.opsForList().rightPush("lstbrand", lstBrand);
		}
		return brand;
	}

	@Override
	@Transactional
	public Brand createBrand(Brand brand) {
		log.info("createBrand start");
		// Check if the brand name already exists
		if (brandRepository.findByBrandName(brand.getBrandName()) != null) {
			throw new RuntimeException("Brand name already exists");
		}

		// Set the brand status to true by default
		brand.setBrandStatus(true);
		brand.setBrandCreatedDate(LocalDate.now());

		return brandRepository.save(brand);
	}

	@Override
	@Transactional
	public Brand updateBrand(Long id, Brand brand) {
		log.info("updateBrand start");
		// Check if the brand exists
		if (brandRepository.findById(id).isEmpty()) {
			throw new RuntimeException("Brand not found");
		}
		// Check if the brand name already exists
		if (brandRepository.findByBrandName(brand.getBrandName()) != null) {
			throw new RuntimeException("Brand name already exists");
		}
		// Update the brand details
		Brand existingBrand = brandRepository.findById(id).orElseThrow(() -> new RuntimeException("Brand not found"));
		existingBrand.setBrandName(brand.getBrandName());
		existingBrand.setBrandDetail(brand.getBrandDetail());
		existingBrand.setBrandSlug(brand.getBrandSlug());
		existingBrand.setBrandStatus(brand.getBrandStatus());
		existingBrand.setBrandUpdatedDate(LocalDate.now());
		existingBrand.setBrandUpdatedBy(brand.getBrandUpdatedBy());
		// Save the updated brand
		Brand updatedBrand = brandRepository.save(existingBrand);
		// Store the updated brand in Redis
		redisTemplate.opsForValue().set("brand:" + id, updatedBrand);
		// Update the list of brands in Redis
		List<Brand> lstBrand = (List<Brand>) redisTemplate.opsForList().getLast("lstbrand");
		if (lstBrand != null) {
			lstBrand.removeIf(b -> b.getBrandId().equals(id));
			lstBrand.add(updatedBrand);
			redisTemplate.opsForList().rightPush("lstbrand", lstBrand);
		}
		// Return the updated brand
		return updatedBrand;
	}

	@Override
	public void deleteBrand(Long id) {
		log.info("deleteBrand start");
		// Check if the brand exists
		if (brandRepository.findById(id).isEmpty()) {
			throw new RuntimeException("Brand not found");
		}
		// Delete the brand from the database
		brandRepository.deleteById(id);
		// Remove the brand from Redis
		redisTemplate.delete("brand:" + id);
		// Remove the brand from the list in Redis
		List<Brand> lstBrand = (List<Brand>) redisTemplate.opsForList().getLast("lstbrand");
		if (lstBrand != null) {
			lstBrand.removeIf(b -> b.getBrandId().equals(id));
			redisTemplate.opsForList().rightPush("lstbrand", lstBrand);
		}

	}

	@Override
	public Page<Brand> getAllBrands(Pageable pageable) {
		log.info("getAllBrands start");
		Page<Brand> brandPage = brandRepository.findAll(pageable);
		return brandPage;
	}

}
