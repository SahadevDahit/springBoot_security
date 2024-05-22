package com.example.demo.entities;

import jakarta.persistence.*;
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
@Table(name = "students")
public class StudentsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;  // Changed to Long

    @Column(name = "name")
    @NotNull(message = "Name cannot be null")
    private String name;

    @Column(name = "roll_no")
    @NotNull(message = "rollNo cannot be null")
    private Integer roll_no;

    @Column(name = "faculty")
    @NotNull(message = "faculty cannot be null")
    private String faculty;
    
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UsersEntity user;
   
}
