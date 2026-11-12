package com.example.ecommerce.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.ecommerce.dto.CartRequestDto;
import com.example.ecommerce.dto.CartResponseDto;
import com.example.ecommerce.dto.CartitemSearchDto;

public interface CartServiceImp {

	String addcart(CartRequestDto cartrequestdto);

	void updatebyid(Long id, CartRequestDto cartrequestdto);

	void patchcart(Long id, CartRequestDto cartrequestdto);

	CartResponseDto getcart(int page, int size);

	CartResponseDto filteritems(String products, String category);

	

	

}
