package com.example.ecommerce.service.impl;

import com.example.ecommerce.dto.CartRequestDto;
import com.example.ecommerce.dto.CartResponseDto;

public interface CartServiceImp {

	CartResponseDto addcart(CartRequestDto cartrequestdto);

	void updatebyid(Long id, CartRequestDto cartrequestdto);

	void patchcart(Long id, CartRequestDto cartrequestdto);

}
