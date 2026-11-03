package com.example.ecommerce.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Category {
	
	
	public Category() {
		super();
	}
	public Category(Long categoryid, String categorycode, String name, String description, LocalDateTime createdat,
			LocalDateTime updatedat) {
		super();
		this.categoryid = categoryid;
		this.categorycode = categorycode;
		this.name = name;
		this.description = description;
		this.createdat = createdat;
		this.updatedat = updatedat;
	}
	public Long getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(Long categoryid) {
		this.categoryid = categoryid;
	}
	public String getCategorycode() {
		return categorycode;
	}
	public void setCategorycode(String categorycode) {
		this.categorycode = categorycode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDateTime getCreatedat() {
		return createdat;
	}
	public void setCreatedat(LocalDateTime createdat) {
		this.createdat = createdat;
	}
	public LocalDateTime getUpdatedat() {
		return updatedat;
	}
	public void setUpdatedat(LocalDateTime updatedat) {
		this.updatedat = updatedat;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "category_id")
	private Long categoryid ;
	
	private String categorycode;
	
	private String name;
	
	private String description;
	
	@CreatedDate
	@Column(name = "created_at", updatable = false,nullable = false)
	private LocalDateTime createdat;
	@LastModifiedDate
	@Column(name = "updated_at",nullable = false)
	private LocalDateTime updatedat;
	
	

}
