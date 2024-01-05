package com.example.demo.dao;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.StudentsEntity;
import com.example.demo.entities.UsersEntity;
import com.example.demo.repositories.UsersRepository;

@Component
public class UsersDAO_Imple implements UsersDAO {

	private UsersRepository usersRepo;

	public UsersDAO_Imple(UsersRepository usersRepo) {
		this.usersRepo = usersRepo;
	}

	@Override
	public String homePage() {

		return "This is users home page";
	}

	@Override
	public ResponseEntity<UsersEntity> addUser(UsersEntity user) {

		try {
			UsersEntity savedUser = usersRepo.save(user);
			if (savedUser != null) {
				return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			// Handle the exception and return an appropriate response
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<UsersEntity> updateUser(String id, UsersEntity user) {
		try {
			Optional<UsersEntity> optionalExistingUser = usersRepo.findById(id);
			if (optionalExistingUser.isPresent()) {
				UsersEntity existingUser = optionalExistingUser.get();

				if (existingUser.getPassword() != null) {
					existingUser.setPassword(existingUser.getPassword());
				}
				if (existingUser.getRole() != null) {
					existingUser.setRole(existingUser.getRole());
				}
				if (existingUser.getStatus() != null) {
					existingUser.setStatus(existingUser.getStatus());
				}
				usersRepo.save(existingUser);
				
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} catch (Exception e) {
			// Handle the exception and return an appropriate response
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
