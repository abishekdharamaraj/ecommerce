package com.example.ecommerce.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Cart {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cartid;
	
	public Cart() {
		super();
	}

	public Cart(Long cartid, Userentity user, List<CartItem> cartitem, BigDecimal total, String createdat,
			String updatedat) {
		super();
		this.cartid = cartid;
		this.user = user;
		this.cartitem = cartitem;
		this.total = total;
		this.createdat = createdat;
		this.updatedat = updatedat;
	}

	public Long getCartid() {
		return cartid;
	}

	public void setCartid(Long cartid) {
		this.cartid = cartid;
	}

	public Userentity getUser() {
		return user;
	}

	public void setUser(Userentity user) {
		this.user = user;
	}

	public List<CartItem> getCartitem() {
		return cartitem;
	}

	public void setCartitem(List<CartItem> cartitem) {
		this.cartitem = cartitem;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getCreatedat() {
		return createdat;
	}

	public void setCreatedat(String createdat) {
		this.createdat = createdat;
	}

	public String getUpdatedat() {
		return updatedat;
	}

	public void setUpdatedat(String updatedat) {
		this.updatedat = updatedat;
	}

	@OneToOne
	@JoinColumn(name = "user_id")
	private Userentity user;
			
	@OneToMany(mappedBy = "cart",cascade = CascadeType.ALL)

	private List<CartItem> cartitem = new ArrayList<>();
	@Column(name = "total_amount",precision = 10 ,scale = 2)
	private BigDecimal total;
	
	@CreatedDate
	@Column(name = "created_at")
	private String createdat;
	
	@LastModifiedDate
	@Column(name = "updated_at")
	private String updatedat;
	
	
	
	
	
	
	
	
	

}
