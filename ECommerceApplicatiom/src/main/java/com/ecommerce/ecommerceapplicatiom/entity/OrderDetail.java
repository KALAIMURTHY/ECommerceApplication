package com.ecommerce.ecommerceapplicatiom.entity;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class OrderDetail {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	private String orderId;
	
	private String productName;
	
	private double productAmount;
	
	public OrderDetail() {
		
	}

	public OrderDetail(String orderId, String productName, double productAmount) {
		this.orderId = orderId;
		this.productName = productName;
		this.productAmount = productAmount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(double productAmount) {
		this.productAmount = productAmount;
	}
	
}
