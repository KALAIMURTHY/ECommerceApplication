package com.ecommerce.ecommerceapplicatiom.model;

import com.ecommerce.ecommerceapplicatiom.entity.Product;

public class CartItem {

	private Product product;
	
	private float subtotal;
	
	public CartItem() {
		
	}

	public CartItem(Product product, float subtotal) {
		this.product = product;
		this.subtotal = subtotal;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public float getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}
	
}
