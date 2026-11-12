package com.example.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.entity.Cart;
import com.example.ecommerce.entity.CartItem;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.Userentity;

public interface CartItemRepository extends JpaRepository<CartItem,Long>{
	List<CartItem> findAllbyProduct(Product product, Cart cart);
	Optional<CartItem> findByProductAndCart(Product product, Cart cart);

	List<CartItem> findbyCart(Cart cart);



	Page<CartItem> findbyCart(Cart cart, Pageable pageable);



}
