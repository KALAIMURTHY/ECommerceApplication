package com.ecommerce.ecommerceapplicatiom.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.ecommerceapplicatiom.entity.Product;
import com.ecommerce.ecommerceapplicatiom.model.Cart;
import com.ecommerce.ecommerceapplicatiom.model.CartItem;
import com.ecommerce.ecommerceapplicatiom.model.CartManager;
import com.ecommerce.ecommerceapplicatiom.repository.ProductRepository;
import com.ecommerce.ecommerceapplicatiom.repository.ProductService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class ProductController {

	@Autowired
	ProductService productService;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
    private CartManager cartManager;
	
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
	
	@GetMapping("/addtocart/{id}")
	@ResponseBody
	public String addtocart(@PathVariable String id, Optional<Product> product, HttpSession session) {
		product = productRepository.findProductByProductId(id);
		Cart cart = cartManager.getCart(session);
        cart.addItem(product.get());
		
		return "success_"+cart.getItemCount();
	}
	
	@GetMapping("/removefromcart/{id}")
	@ResponseBody
	public String removefromcart(@PathVariable String id, Optional<Product> product, HttpSession session) {
		product = productRepository.findProductByProductId(id);
		Cart cart = cartManager.getCart(session);
        cart.removeItem(product.get());
		
		return "success_"+cart.getItemCount();
	}
	
	@GetMapping("/shoppingcart")
	public String shoppingcart(Model model, HttpSession session) {
		
		Cart cart = cartManager.getCart(session);
		List<CartItem> items = cart.getItems();
		float total = 0.0f;
		for(CartItem item : items) {
			total = total + item.getSubtotal();
		}
		model.addAttribute("total", total);
		model.addAttribute("items", items);
		return "shoppingcart";
	}
}
