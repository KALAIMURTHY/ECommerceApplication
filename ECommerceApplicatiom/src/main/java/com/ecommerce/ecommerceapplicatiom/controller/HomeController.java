package com.ecommerce.ecommerceapplicatiom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ecommerce.ecommerceapplicatiom.entity.Product;
import com.ecommerce.ecommerceapplicatiom.repository.ProductRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	ProductRepository productRepository;

	@GetMapping("/")
	public String home(Model model, HttpSession session) {
		session.invalidate();
		List<Product> products = productRepository.findAll();
		model.addAttribute("products",products);
		return "home";
	}
	
	@GetMapping("/home")
	public String homeInvalidateSessions(Model model, HttpSession session) {
		session.invalidate();
		List<Product> products = productRepository.findAll();
		model.addAttribute("products",products);
		return "home";
	}
	
}
