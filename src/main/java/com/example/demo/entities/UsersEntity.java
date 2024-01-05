package com.example.demo.entities;

import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "users")
public class UsersEntity {
	
	@Id
	@Column(name = "id")
	@NotNull(message = "id cannot be null")
	private String id=UUID.randomUUID().toString();

	@Column(name = "email")
	@NotNull(message = "email cannot be null")
	private String email;

	@Column(name = "password")
	@NotNull(message = "password cannot be null")
	private String password;

	@Column(name = "role")
	@NotNull(message = "Role cannot be null")
	private String role;
	
	@Column(name = "status")
	@NotNull(message = "Status cannot be null")
	private Boolean status = true;
	public UsersEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "UsersEntity [id=" + id + ", email=" + email + ", password=" + password + ", role=" + role + ", status="
				+ status + "]";
	}

	public UsersEntity(@NotNull(message = "id cannot be null") String id,
			@NotNull(message = "email cannot be null") String email,
			@NotNull(message = "password cannot be null") String password,
			@NotNull(message = "Role cannot be null") String role,
			@NotNull(message = "Status cannot be null") Boolean status) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.role = role;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		// Use BCryptPasswordEncoder to encode the password before setting
        if (password != null && !password.isEmpty()) {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            this.password = passwordEncoder.encode(password);
        } else {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	
	
}

