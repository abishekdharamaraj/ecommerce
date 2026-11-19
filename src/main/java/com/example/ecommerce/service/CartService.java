package com.example.ecommerce.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.ecommerce.dto.CartItemResponseDto;
import com.example.ecommerce.dto.CartRequestDto;
import com.example.ecommerce.dto.CartResponseDto;
import com.example.ecommerce.entity.Cart;
import com.example.ecommerce.entity.CartItem;
import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.Userentity;
import com.example.ecommerce.excepton.RequestNullException;
import com.example.ecommerce.excepton.ResourceNotFoundException;
import com.example.ecommerce.excepton.UserNotFoundException;
import com.example.ecommerce.repository.CartItemRepository;
import com.example.ecommerce.repository.CartRepository;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.UserReository;
import com.example.ecommerce.service.impl.CartServiceImp;

import jakarta.transaction.Transactional;
@Service
public class CartService implements CartServiceImp{
	

	private final UserReository uspero;
	

	private final CartRepository cartrepo;
	

	private final ProductRepository productrepo;
	

	private final CartItemRepository cartitems;
	

	private final CategoryRepository categoryrepo;
	
	public CartService(UserReository uspero, CartRepository cartrepo,ProductRepository productrepo,CartItemRepository cartitems,CategoryRepository categoryrepo){
		this.uspero = uspero;
		this.cartrepo = cartrepo;
		this.cartitems = cartitems;
		this.categoryrepo = categoryrepo;
		this.productrepo = productrepo;
		
	}


	@Override
	@Transactional
	public String addcart(CartRequestDto cartrequestdto) {
		if(cartrequestdto == null) {
			throw new RequestNullException("Invalid Request");
			
		}
		Long id = cartrequestdto.getProductid();
		System.out.println(id);
		Authentication  authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		System.out.println(email);
		Userentity user = uspero.findByEmail(email).orElseThrow(() -> new RuntimeException("Email not found"));

		Cart cart = cartrepo.findByUser(user).orElseGet(() -> {
			Cart newCart = new Cart();
            newCart.setUser(user);
            
            return cartrepo.save(newCart);
		});
		System.out.println(cart);
		
		Product pro = productrepo.findById(id).orElseThrow(() -> new RuntimeException("product not found"));
		
		System.out.println(pro);
		
	     
        CartItem item = new CartItem();
        List<CartItem> existingItem = cartitems.findAllByProductAndCart(pro, cart);
        System.out.println( "cartitems..."+ item);
	     if (!existingItem.isEmpty()) {
	    	item = existingItem.get(0);
	    	System.out.println("Updated Quantity: " + item.getQuantity());
	    	System.out.println("Updated Subtotal: " + item.getSubtotal());
	    	System.out.println("Cart Total: " + cart.getTotal());
	    	item.setQuantity( item.getQuantity().add(cartrequestdto.getQuantity()));
	    	  item.setSubtotal(
	    		        item.getPrice().multiply(item.getQuantity())
	    		    );
	     }
	     
	     else {
		item.setProduct(pro);
		item.setPrice(pro.getPrice());
		item.setCart(cart);
		BigDecimal subtotal = pro.getPrice().multiply(cartrequestdto.getQuantity());
		System.out.println( "cartitems..."+ subtotal);
		item.setSubtotal(subtotal);
		item.setQuantity(cartrequestdto.getQuantity());
		
		CartItem cartitem_saved = cartitems.save(item);
		System.out.println( "cartitems..."+ cartitem_saved);
		BigDecimal total = cart.getCartitem()
		        .stream()
		        .map(CartItem::getSubtotal)
		        .reduce(BigDecimal.ZERO, BigDecimal::add);

		  cart.setTotal(total);
	     }
		  Cart cart_saved =cartrepo.save(cart);
		  
		  CartResponseDto response = new CartResponseDto();
		  
		  response.setTotal(cart_saved.getTotal());
		  return "Created";
		  
	}
	


	@Override
	@Transactional
	public void updatebyid(Long id, CartRequestDto cartrequestdto) {

		
		if(cartrequestdto == null) {
			throw new RequestNullException("Invalid Request");
		}
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		String Email = authentication.getName();
		Userentity user = uspero.findByEmail(Email).orElseThrow(() -> new ResourceNotFoundException("No User Found"));
		System.out.println(user);
		Cart cart = cartrepo.findByUserAndCartid(user, id).orElseThrow(() -> new ResourceNotFoundException("No Cart Found"));
		Product product = productrepo.findById(cartrequestdto.getProductid()).orElseThrow(() -> new ResourceNotFoundException("No product Found"));
		
		CartItem cartitem =cartitems.findByProductAndCart(product, cart).orElseThrow(() ->  new ResourceNotFoundException("No Cart Item found"));
		
		cartitem.setProduct(product);
		cartitem.setQuantity(cartrequestdto.getQuantity());
		cartitem.setSubtotal(product.getPrice().multiply(cartrequestdto.getQuantity()));
		
		BigDecimal total = BigDecimal.ZERO;
		for (CartItem item : cart.getCartitem()) {
		    total = total.add(item.getSubtotal());
		}
		
		cart.setTotal(total);
		
		cartrepo.save(cart);


		
		
	}


	@Override
	public void patchcart(Long id, CartRequestDto cartrequestdto) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		Userentity user =uspero.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("No User Found"));
		Cart cart = cartrepo.findByUserAndCartid(user, id).orElseThrow(() -> new ResourceNotFoundException("No Cart Found"));
		Product product = productrepo.findById(cartrequestdto.getProductid()).orElseThrow(() -> new ResourceNotFoundException("No product Found"));
		CartItem cartitem = cartitems.findByProductAndCart(product, cart).orElseThrow(() -> new ResourceNotFoundException("No Cartitem Found"));
		
		
		if(cartrequestdto.getQuantity().compareTo(BigDecimal.ZERO) <= 0) {
			
			throw new RuntimeException("invalid");
			
		}
		
		cartitem.setQuantity(cartrequestdto.getQuantity().subtract(cartitem.getQuantity()));
		cartitem.setPrice(product.getPrice().subtract(cartitem.getPrice()));
		cartitem.setSubtotal(product.getPrice().subtract(cartitem.getSubtotal()));
		
		BigDecimal total = cart.getTotal();
		cart.setTotal(total.subtract(product.getPrice()));
		
		cartrepo.save(cart);
		
	}


	@Override
	public CartResponseDto getcart(int page, int size) {
		if (page < 0 || size <= 0) {
		    throw new IllegalArgumentException("Invalid pagination values");
		}
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String Email = authentication.getName();
		Userentity users = uspero.findByEmail(Email).orElseThrow(() -> new RuntimeException("No user Found"));
		Cart cart = cartrepo.findByUser(users).orElseThrow(() -> new UserNotFoundException("No user Found"));
	
		Pageable pageable = PageRequest.of(page, size);
		Page<CartItem> cartitem =  cartitems.findByCart(cart,pageable);
		
		Page<CartItemResponseDto> responsecartitem = cartitem.map(item -> {
			
			CartItemResponseDto dto = new CartItemResponseDto();
			dto.setProduct(item.getProduct().getName());
			dto.setQuantity(item.getQuantity());
			dto.setPrice(item.getPrice());
			dto.setSubtotal(item.getSubtotal());
		
			return dto;
		});
		
		CartResponseDto response = new CartResponseDto();
		
		response.setCartitem(responsecartitem);
		response.setTotal(cart.getTotal());
		

	    return response;
	 	
	}


	@Override
	public CartResponseDto filteritems(String products, String category) {
		
		if(products == null && category == null) {
			throw new IllegalArgumentException("not found");
		}
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		Userentity user = uspero.findByEmail(email).orElseThrow(() -> new RuntimeException("not found"));
		Cart cart = cartrepo.findByUser(user).orElseThrow(() -> new RuntimeException("not found"));
		
		Product product = productrepo.findByName(products).orElseThrow(() -> new RuntimeException("not found"));
		Category categorys = categoryrepo.findByName(category).orElseThrow(() -> new RuntimeException("not found"));
		Product productss = productrepo.findByCategoryid(categorys).orElseThrow(() -> new RuntimeException("not found"));
		
		List<CartItem> cartitem = cartitems.findAllByProduct(product,cart);
		
		
		List<CartItemResponseDto> responseitem = cartitem.stream()
				.map(item -> {
					CartItemResponseDto dto = new CartItemResponseDto();
					dto.setProduct(item.getProduct().getName());
					dto.setPrice(item.getPrice());
					dto.setQuantity(item.getQuantity());
					dto.setSubtotal(item.getSubtotal());					
					return dto;
				}).collect(Collectors.toList());
		
		CartResponseDto responses = new CartResponseDto();
		responses.setCartitemv1(responseitem);
		
		return responses;
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}


	

	
	
	
	


}
