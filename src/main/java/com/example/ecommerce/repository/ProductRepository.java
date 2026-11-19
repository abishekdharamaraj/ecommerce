package com.example.ecommerce.repository;

import java.util.List;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.Product;


public interface ProductRepository extends JpaRepository<Product,Long>{
	List<Product> findByNameContainingIgnoreCase(String name);
	
	List<Product> findByPriceBetween(Double min, Double max);
	
	
	@Query("""
            SELECT p
            FROM Product p
            WHERE
            (:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')))
            AND
            (:categoryId IS NULL OR p.categoryid.categoryid = :categoryId)
            AND
            (:price IS NULL OR p.price <= :price)
            """)
	Page<Product> filterproducts(@Param("name") String name,@Param("price") double price, @Param("categoryid") Long categoryid ,Pageable pageable);

	Optional<Product> findByName(String products);
	
	Optional<Product>findByCategoryid(Category category);
	
	


}
