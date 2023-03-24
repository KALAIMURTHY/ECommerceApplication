package com.ecommerce.ecommerceapplicatiom.entity;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Product {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String productId;

	private String productName;

	private float productPrice;

	@Lob
	private byte[] productImg;
	
	private String productImgName;
	
	private int inStock;

	public Product() {

	}

	public String getProductImgName() {
		return productImgName;
	}

	public void setProductImgName(String productImgName) {
		this.productImgName = productImgName;
	}

	public Product(String productName, float productPrice, byte[] productImg, String productImgName, int inStock) {
		this.productName = productName;
		this.productPrice = productPrice;
		this.productImg = productImg;
		this.productImgName = productImgName;
		this.inStock = inStock;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}

	public byte[] getProductImg() {
		return productImg;
	}

	public void setProductImg(byte[] productImg) {
		this.productImg = productImg;
	}

	public int getInStock() {
		return inStock;
	}

	public void setInStock(int inStock) {
		this.inStock = inStock;
	}

	public String getProductId() {
		return productId;
	}	

}
