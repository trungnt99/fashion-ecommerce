package com.gnurt.fashion_ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gnurt.fashion_ecommerce.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
