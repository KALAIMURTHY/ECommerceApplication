package com.ecommerce.ecommerceapplicatiom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerceapplicatiom.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

	Optional<Product> findProductByProductId(String id);

	Optional<Product> findProductByProductName(String productName);

	void deleteProductByProductId(String id);

}
