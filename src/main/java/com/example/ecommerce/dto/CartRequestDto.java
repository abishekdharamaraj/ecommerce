package com.example.ecommerce.dto;

import java.math.BigDecimal;
import java.util.List;



public class CartRequestDto {
	




	public Long getProductid() {
		return productid;
	}
	public void setProductid(Long productid) {
		this.productid = productid;
	}
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}


	public CartRequestDto() {
		super();
	}
	public CartRequestDto( Long productid, BigDecimal quantity) {
		super();

		this.productid = productid;
		this.quantity = quantity;


	}
	

	private Long productid;
	private BigDecimal quantity;
	

	


}

