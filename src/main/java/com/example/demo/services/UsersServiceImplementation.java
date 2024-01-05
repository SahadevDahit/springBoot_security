package com.example.demo.services;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.demo.dao.UsersDAO;
import com.example.demo.entities.UsersEntity;

@Service
public class UsersServiceImplementation implements UsersService {

	
	private UsersDAO users_dao;

	public UsersServiceImplementation(UsersDAO users_dao) {
		this.users_dao = users_dao;
	}

	@Override
	public String homePage() {
		// TODO Auto-generated method stub
		return users_dao.homePage();
	}

	@Override
	public ResponseEntity<UsersEntity> addUser(UsersEntity user) {
		return users_dao.addUser(user);

	}
	
	@Override
	public ResponseEntity<UsersEntity> updateUser(String id,UsersEntity user){
		return users_dao.updateUser(id,user);
	}
}
