package com.example.ecommerce.dto;

import java.math.BigDecimal;
import java.util.List;



public class CartRequestDto {
	



	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
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
	public List<?> getCartitem() {
		return cartitem;
	}
	public void setCartitem(List<?> cartitem) {
		this.cartitem = cartitem;
	}
	
	public CartRequestDto() {
		super();
	}
	public CartRequestDto(Long userid, Long productid, BigDecimal quantity, List<?> cartitem) {
		super();
		this.userid = userid;
		this.productid = productid;
		this.quantity = quantity;
		this.cartitem = cartitem;

	}
	
	private Long userid;
	private Long productid;
	private BigDecimal quantity;
	private List<?> cartitem;

	


}

