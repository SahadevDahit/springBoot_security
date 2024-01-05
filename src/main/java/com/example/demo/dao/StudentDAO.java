package com.example.demo.dao;

import java.util.List;
import org.springframework.http.ResponseEntity;

import com.example.demo.entities.StudentsEntity;

public interface StudentDAO {

	public String homePage();

	public ResponseEntity<StudentsEntity> addStudent(StudentsEntity student);

	public ResponseEntity<?> addBulkStudents(List<StudentsEntity> students);

	public ResponseEntity<List<StudentsEntity>> getAllStudent();

	public ResponseEntity<StudentsEntity> getStudentById(long id);

	public  ResponseEntity<String> updateStudent(long id, StudentsEntity student);

	public ResponseEntity<String> deleteStudent(long id);

	public ResponseEntity<String> deleteStudentAll();
}
