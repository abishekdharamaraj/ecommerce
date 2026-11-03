package com.example.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.entity.Cart;
import com.example.ecommerce.entity.Userentity;

public interface CartRepository extends JpaRepository<Cart,Long>{
	
	Optional <Cart> findByUser(Userentity user);
	
	Optional <Cart>findByUserAndCartid(Userentity user,Long id);
	
	
	
}
