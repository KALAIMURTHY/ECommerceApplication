package com.ecommerce.ecommerceapplicatiom.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.ecommerceapplicatiom.model.Product;
import com.ecommerce.ecommerceapplicatiom.repository.ProductRepository;
import com.ecommerce.ecommerceapplicatiom.repository.ProductService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ProductController {

	@Autowired
	ProductService productService;
	
	@Autowired
	ProductRepository productRepository;
	
	@GetMapping("/newproduct")
	public String newproduct(Model model) {
		model.addAttribute("product", new Product());
		return "newproduct";
	}
	
	@PostMapping("/newproduct")
	public String addnewproduct(Model model, Product product, @RequestParam("file") MultipartFile file) {
		productService.saveNewProduct(product, file);
		model.addAttribute("message", "File uploaded successfully");
		return "newproduct";
	}
	
	
	@GetMapping("/image")
	public void showImage(@Param("id") String id, HttpServletResponse response, Optional<Product> product)
			throws ServletException, IOException {

		product = productRepository.findProductByProductId(id);
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif, image/pdf");
		response.getOutputStream().write(product.get().getProductImg());
		response.getOutputStream().close();
	}
	
}
