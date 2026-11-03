package com.example.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.dto.RoleRequestDto;
import com.example.ecommerce.dto.RoleResponseDto;
import com.example.ecommerce.service.impl.RoleServiceImp;

@RestController
@RequestMapping("/api/role")
public class RoleController {
	
	@Autowired
	public RoleServiceImp roleserviceimp;
	
	@PostMapping
	public String addrole(@RequestBody RoleRequestDto rolereqeuestdto  ) {
		
		 roleserviceimp.addrole(rolereqeuestdto);
		 return "saved sucessfully";
	}
	
	@GetMapping
	public Page<RoleResponseDto> getrole(@RequestParam(defaultValue = "0") int page , @RequestParam(defaultValue = "1") int size){
		return roleserviceimp.getrole(page,size);
	}
	
	@GetMapping("/{id}")
	public RoleResponseDto getrolebyid(@PathVariable Long id) {
		
		return roleserviceimp.getrolebyid(id);
	}
	
	@PutMapping("/{id}")
	public RoleResponseDto putrolebyid(@PathVariable Long id, @RequestBody RoleRequestDto rolerequestdto) {
		return roleserviceimp.putrolebyid(id ,rolerequestdto);
	}
	
	@DeleteMapping("/{id}")
	public String deletebyid(@PathVariable Long id) {
		
		 roleserviceimp.deletebyid(id);
		 return "deleted sucessfully";
	}
}

