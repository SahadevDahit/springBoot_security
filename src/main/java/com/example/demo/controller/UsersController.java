package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.demo.entities.UsersEntity;
import com.example.demo.services.UsersService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/users")
public class UsersController {
	@Autowired
	private UsersService usersService;

	@RequestMapping("/usersHome")
	@Operation(summary = "This is user home page")
	public String usersHome() {
		return usersService.homePage();
	}


	
	@PostMapping("/newUser")
	@Operation(summary = "adding new user")
	public ResponseEntity<UsersEntity> addUser(@RequestBody UsersEntity user) {
		return usersService.addUser(user);
	}
	
	@PatchMapping("/udateUser")
	@Operation(summary = "updating user")
	public ResponseEntity<UsersEntity> updateUser(String id,@RequestBody UsersEntity user){
		
		return usersService.updateUser(id,user);
	}
	
}
