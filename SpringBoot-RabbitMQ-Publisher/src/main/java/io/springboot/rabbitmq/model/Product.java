package io.springboot.rabbitmq.model;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class Product implements Serializable {
	

	/**
	 * 
	 */
	private int productId;
	private int quantity;
	private String name;
	
	public Product() {
		
	}
	
	
	public Product(int productId, int quantity, String name) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		this.name = name;
	}
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", quantity=" + quantity + ", name=" + name + "]";
	}
	
	

}
