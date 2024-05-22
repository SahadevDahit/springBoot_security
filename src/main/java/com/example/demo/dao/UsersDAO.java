package com.example.demo.dao;

import org.springframework.http.ResponseEntity;

import com.example.demo.entities.UsersEntity;

public interface UsersDAO {
	public String homePage();
	public ResponseEntity<UsersEntity> addUser(UsersEntity user);
	public ResponseEntity<UsersEntity> updateUser(String id,UsersEntity user);
	public UsersEntity getUserFromToken();
}
