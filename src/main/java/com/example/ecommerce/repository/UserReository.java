package com.example.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.entity.Userentity;


public interface UserReository extends JpaRepository<Userentity,Long>{
	
	Optional<Userentity> findByEmail(String email);
	
	

}
