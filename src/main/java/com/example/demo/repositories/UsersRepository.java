package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.UsersEntity;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, String> {
	  UsersEntity findByEmail(String email);

}
