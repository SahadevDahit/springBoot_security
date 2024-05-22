package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.entities.UsersEntity;
import com.example.demo.repositories.UsersRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UsersRepository userRepo;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UsersEntity user = userRepo.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        } else {
            return new CustomUser(user);
        }
    }
}
