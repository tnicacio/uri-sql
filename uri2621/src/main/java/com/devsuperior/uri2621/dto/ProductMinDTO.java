package com.devsuperior.uri2621.dto;

import com.devsuperior.uri2621.entities.Product;
import com.devsuperior.uri2621.projections.ProductMinProjection;

public class ProductMinDTO {

	String name;

	public ProductMinDTO() {}
	
	public ProductMinDTO(String name) {
		this.name = name;
	}
	
	public ProductMinDTO(ProductMinProjection projection) {
		name = projection.getName();
	}
	
	public ProductMinDTO(Product entity) {
		name = entity.getName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ProductMinDTO [name=" + name + "]";
	}
	
}
