package com.ecommerce.ecommerceapplicatiom.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.ecommerceapplicatiom.entity.Order;
import com.ecommerce.ecommerceapplicatiom.entity.Product;
import com.ecommerce.ecommerceapplicatiom.repository.OrderRepository;
import com.ecommerce.ecommerceapplicatiom.repository.ProductRepository;
import com.ecommerce.ecommerceapplicatiom.repository.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ProductRepository productRepository;
	
	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		List<Product> products = productRepository.findAll();
		model.addAttribute("products",products);
		return "adminDashboard";
	}	
	
	@GetMapping("/orders")
	public String orders(Model model) {
		
		List<Order> orders = orderRepository.findAll();
		model.addAttribute("orders",orders);
		return "ordersPage";
	}
	
	@GetMapping("/addproduct")
	public String addproduct(Model model) {
		model.addAttribute("product", new Product());
		return "addproduct";
	}
	
	@PostMapping("/addproduct")
	public String addnewproduct(Model model, Product product, @RequestParam("file") MultipartFile file) {
		Optional<Product> findProductByProductName = productRepository.findProductByProductName(product.getProductName());
		if(findProductByProductName.isPresent())
			model.addAttribute("message", "alreadyexist");
		else {
			productService.saveNewProduct(product, file);
			model.addAttribute("message", "success");
		}
		return "addproduct";
	}
	
	@PostMapping(value = "/updateproduct", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseBody
	public String updateproduct(@ModelAttribute Product product) {
		
		return "result";
	}
}
