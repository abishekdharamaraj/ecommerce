package com.example.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.dto.CartRequestDto;
import com.example.ecommerce.service.impl.CartServiceImp;

import jakarta.validation.constraints.Min;

@RestController
@Validated
@RequestMapping("/api/cart")
public class Cartcontroller {
	
	@Autowired
	private CartServiceImp cartimp;
	
	@PostMapping
	public ResponseEntity addcart(@RequestBody CartRequestDto cartrequestdto ) {
		cartimp.addcart(cartrequestdto);
		 
		 return ResponseEntity.status(HttpStatus.CREATED).body("created");

}
	@PutMapping
	public ResponseEntity updatecart(@PathVariable @Min(1) Long id,@RequestBody CartRequestDto cartrequestdto) {
		cartimp.updatebyid(id,cartrequestdto);
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body("updated");
		
		
		
	}
	@PatchMapping("/id")
	public ResponseEntity patchcart(@PathVariable Long id,@RequestBody CartRequestDto cartrequestdto) {
		cartimp.patchcart(id,cartrequestdto);
		return ResponseEntity.status(HttpStatus.CREATED).body("patched");
	}
	

}
