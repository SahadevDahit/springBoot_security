package com.example.demo.entities;

import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UsersEntity {

    @Id
    @Column(name = "id")
    @NotNull(message = "id cannot be null")
    private String id = UUID.randomUUID().toString();

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
    

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private StudentsEntity student;
    
    public void setPassword(String password) {
        // Use BCryptPasswordEncoder to encode the password before setting
        if (password != null && !password.isEmpty()) {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            this.password = passwordEncoder.encode(password);
        } else {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
    }
}
