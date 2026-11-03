package com.example.ecommerce.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name ="RefershTokenEntity")

public class RefershTokenEntity {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long generateid;
	

	public Long getGenerateid() {
		return generateid;
	}

	public void setGenerateid(Long generateid) {
		this.generateid = generateid;
	}

	public String getRefershtoken() {
		return refershtoken;
	}

	public void setRefershtoken(String refershtoken) {
		this.refershtoken = refershtoken;
	}

	public LocalDateTime getExpirydate() {
		return expirydate;
	}

	public void setExpirydate(LocalDateTime expirydate) {
		this.expirydate = expirydate;
	}

	public LocalDateTime getOrginalexpirydate() {
		return orginalexpirydate;
	}

	public void setOrginalexpirydate(LocalDateTime orginalexpirydate) {
		this.orginalexpirydate = orginalexpirydate;
	}

	public LocalDateTime getCreateddate() {
		return createddate;
	}

	public void setCreateddate(LocalDateTime createddate) {
		this.createddate = createddate;
	}

	public Userentity getUser() {
		return user;
	}

	public void setUser(Userentity user) {
		this.user = user;
	}

	public RefershTokenEntity() {
		super();
	}

	public RefershTokenEntity(Long generateid, String refershtoken, LocalDateTime expirydate,
			@NotBlank LocalDateTime orginalexpirydate, LocalDateTime createddate, Userentity user) {
		super();
		this.generateid = generateid;
		this.refershtoken = refershtoken;
		this.expirydate = expirydate;
		this.orginalexpirydate = orginalexpirydate;
		this.createddate = createddate;
		this.user = user;
	}

	@Column(nullable = false,unique = true)
	private String refershtoken;
	
	@Column(nullable = false)
	private LocalDateTime expirydate;
	

	private LocalDateTime orginalexpirydate;
	
	@CreatedDate
	private LocalDateTime createddate;
	
	@OneToOne(fetch = FetchType.EAGER)	
    @JoinColumn(name = "user_id",nullable = false)
	private Userentity user;
	

}
