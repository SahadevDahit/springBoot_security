package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.StudentsEntity;
import com.example.demo.services.StudentsService;

import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/students")
public class StudentsController {

	@Autowired
	StudentsService studentService;

	@RequestMapping("/")
	@Operation(summary="This is default home page")
	public String viewHome() {
		
		return studentService.Home();
	}
	
	@RequestMapping("/login")
	@Operation(summary="This is login page")
	public String login() {
		return "Login Please !!!";
	}
	

	

	@GetMapping("/getAll")
	@Operation(summary="get all students list")
	public ResponseEntity<List<StudentsEntity>> getAllStudent() {
		return studentService.getAllStudent();
	}

	@GetMapping("/students/{id}")
	@Operation(summary="get student record by id")
	public ResponseEntity<StudentsEntity> getStudentById(@PathVariable Long id) {
		return studentService.getStudentById(id);
	}

	@PostMapping("/newStudent")
	@Operation(summary="add new Student")
	public ResponseEntity<StudentsEntity> addStudent(@RequestBody StudentsEntity student) {
		return studentService.addStudent(student);

	}

	@PostMapping("/saveAll")
	@Operation(summary="save students list")
	public ResponseEntity<?> addBulkStudent(@RequestBody List<StudentsEntity> students) {
		return studentService.addBulkStudents(students);
	}

	@PatchMapping("/{id}")
	@Operation(summary="update student record by id")
	public ResponseEntity<String> updateStudent(@PathVariable Long id, @RequestBody StudentsEntity student) {
		return studentService.updateStudent(id, student);
	}

	@DeleteMapping("/{id}")
	@Operation(summary="delete student by id")
	public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
		return studentService.deleteStudent(id);
	}

	@DeleteMapping("/")
	@Operation(summary="delete all student by id")
	public ResponseEntity<String> deleteStudentAll() {
		return studentService.deleteStudentAll();
	}

}
