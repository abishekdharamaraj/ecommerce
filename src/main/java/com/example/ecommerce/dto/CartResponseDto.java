package com.example.ecommerce.dto;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;



public class CartResponseDto {
	
	
	public CartResponseDto() {
		super();
	}
	public CartResponseDto(Long cartid, Page<CartItemResponseDto> cartitem, List<CartItemResponseDto> cartitemv1,
			BigDecimal total, Long userid) {
		super();
		this.cartid = cartid;
		this.cartitem = cartitem;
		this.cartitemv1 = cartitemv1;
		this.total = total;
		this.userid = userid;
	}
	public Long getCartid() {
		return cartid;
	}
	public void setCartid(Long cartid) {
		this.cartid = cartid;
	}
	public Page<CartItemResponseDto> getCartitem() {
		return cartitem;
	}
	public void setCartitem(Page<CartItemResponseDto> cartitem) {
		this.cartitem = cartitem;
	}
	public List<CartItemResponseDto> getCartitemv1() {
		return cartitemv1;
	}
	public void setCartitemv1(List<CartItemResponseDto> cartitemv1) {
		this.cartitemv1 = cartitemv1;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	private Long cartid;
	private Page<CartItemResponseDto> cartitem ;
	private List<CartItemResponseDto> cartitemv1;
	private BigDecimal total;
	private Long userid;



}
