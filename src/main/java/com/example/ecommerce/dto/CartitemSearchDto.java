package com.example.ecommerce.dto;

public class CartitemSearchDto {
	
	public CartitemSearchDto() {
		super();
	}
	public CartitemSearchDto(String product, String category) {
		super();
		this.product = product;
		Category = category;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	String product;
	String Category;

}
