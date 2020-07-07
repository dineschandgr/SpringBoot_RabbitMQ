package io.springboot.rabbitmq.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProductEntity {
	
	@Id
	private int productId;
	private int quantity;
	private String name;
	
	public ProductEntity() {
		
	}
	
	public ProductEntity(int productId, int quantity, String name) {
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
