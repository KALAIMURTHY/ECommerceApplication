package com.ecommerce.ecommerceapplicatiom.repository;

import java.nio.file.FileAlreadyExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.ecommerceapplicatiom.entity.Product;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	/*
	 * public void saveNewProduct(Product product, MultipartFile file) { try {
	 * Files.copy(file.getInputStream(),
	 * Paths.get("./productImages").resolve(file.getOriginalFilename()));
	 * product.setProductImg("/productImages/"+file.getOriginalFilename());
	 * productRepository.save(product); } catch (Exception e) { if (e instanceof
	 * FileAlreadyExistsException) { throw new
	 * RuntimeException("A file of that name already exists."); }
	 * 
	 * throw new RuntimeException(e.getMessage()); } }
	 */

	public void saveNewProduct(Product product, MultipartFile file) {
		try {
			product.setProductImg(file.getBytes());
			product.setProductImgName(file.getOriginalFilename());
			productRepository.save(product);
		} catch (Exception e) {
			if (e instanceof FileAlreadyExistsException) {
				throw new RuntimeException("A file of that name already exists.");
			}

			throw new RuntimeException(e.getMessage());
		}
	}
	
	
	
}
