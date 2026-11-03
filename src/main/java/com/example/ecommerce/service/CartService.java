package com.example.ecommerce.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.ecommerce.dto.CartItemResponseDto;
import com.example.ecommerce.dto.CartRequestDto;
import com.example.ecommerce.dto.CartResponseDto;
import com.example.ecommerce.entity.Cart;
import com.example.ecommerce.entity.CartItem;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.Userentity;
import com.example.ecommerce.repository.CartItemRepository;
import com.example.ecommerce.repository.CartRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.UserReository;
import com.example.ecommerce.service.impl.CartServiceImp;

import jakarta.transaction.Transactional;
@Service
public class CartService implements CartServiceImp{
	
	@Autowired
	private UserReository uspero;
	
	@Autowired
	private CartRepository cartrepo;
	
	@Autowired
	private ProductRepository productrepo;
	
	@Autowired
	private CartItemRepository cartitems;


	@Override
	@Transactional
	public CartResponseDto addcart(CartRequestDto cartrequestdto) {
		

		Long id = cartrequestdto.getProductid();
		Authentication  authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		Userentity user = uspero.findByEmail(email).orElseThrow(() -> new RuntimeException("Email not found"));

		Cart cart = cartrepo.findByuser(user).orElseGet(() -> {
			Cart newCart = new Cart();
            newCart.setUser(user);
            return cartrepo.save(newCart);
		});
		
		Product pro = productrepo.findById(id).orElseThrow(() -> new RuntimeException("product not found"));
		
	     
        CartItem item = new CartItem();
        Optional<CartItem> existingItem = cartitems.findByProductAndCart(pro, cart);
	     if (existingItem.isPresent()) {
	    	 item = existingItem.get();
	    	 System.out.println("thiss...................." + item);
	    	item.setQuantity( item.getQuantity().add(cartrequestdto.getQuantity()));
	     }
	     else {
		item.setProduct(pro);
		item.setPrice(pro.getPrice());
		item.setCart(cart);
		BigDecimal subtotal = pro.getPrice().multiply(cartrequestdto.getQuantity());
		item.setSubtotal(subtotal);
		item.setQuantity(cartrequestdto.getQuantity());
		
		CartItem cartitem_saved = cartitems.save(item);
		BigDecimal total = cart.getCartitem()
		        .stream()
		        .map(CartItem::getSubtotal)
		        .reduce(BigDecimal.ZERO, BigDecimal::add);

		  cart.setTotal(total);
	     }
		  Cart cart_saved =cartrepo.save(cart);
		  
		  CartResponseDto response = new CartResponseDto();
		  
		  response.setTotal(cart_saved.getTotal());
		  List<CartItemResponseDto> cartitem = new ArrayList<>();
		  
		  for(CartItem cartitm : cart.getCartitem()) {
			  
			  CartItemResponseDto dto = new CartItemResponseDto();
			  dto.setProduct(cartitm.getProduct().getName());
			  dto.setPrice(cartitm.getPrice());
			  dto.setQuantity(cartitm.getQuantity());
			  dto.setSubtotal(cartitm.getSubtotal());
			  
			  cartitem.add(dto);
		  }
		  
		  response.setCart(cartitem);
		
		return response;

	}


	@Override
	public void updatebyid(Long id, CartRequestDto cartrequestdto) {
		// TODO Auto-generated method stub
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String Email = auth.getName();
		Userentity user = uspero.findByEmail(Email).orElseThrow(()-> new RuntimeException("No user Found"));
		cartrepo.findByuser(user).orElseThrow(()-> new RuntimeException("No Cart Found"));
		Cart carts = cartrepo.findById(id).orElseThrow(()-> new RuntimeException("No Cart Found"));
		Product product = productrepo.findById(cartrequestdto.getProductid()).orElseThrow(()-> new RuntimeException("No product Found"));
		CartItem cartitem = new CartItem();
		
		
		
		
		
		
	}
	


}
