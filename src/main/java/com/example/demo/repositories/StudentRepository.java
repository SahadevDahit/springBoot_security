package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.StudentsEntity;
@Repository
public interface StudentRepository extends JpaRepository<StudentsEntity,Long> {

     @Query(value = "SELECT * FROM students WHERE user_id = :userId", nativeQuery = true)
	   StudentsEntity getStudentDetailsByUserId(@Param("userId") String userId);
	

}
