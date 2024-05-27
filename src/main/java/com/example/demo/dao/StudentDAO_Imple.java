package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.demo.config.CustomUser;
import com.example.demo.controller.UsersController;
import com.example.demo.entities.StudentsEntity;
import com.example.demo.entities.UsersEntity;
import com.example.demo.exceptions.NoSuchElementException;
import com.example.demo.repositories.StudentRepository;
import com.example.demo.services.UsersService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class StudentDAO_Imple implements StudentDAO {
	private StudentRepository studentRepo;

	@Autowired
	private UsersController userController;
	
	public StudentDAO_Imple(StudentRepository studentRepo) {
		this.studentRepo = studentRepo;
	}

	public String homePage() {
		   UsersEntity user = userController.getUserFromToken();
		    log.info("userDetails is ",user.getId());
	      return "Welcome ....."+ user.getId();
	}

	public ResponseEntity<StudentsEntity> addStudent(StudentsEntity student) {
		try {
//			log.info("adding student");
			UsersEntity user = userController.getUserFromToken();	
			student.setUser(user);
			StudentsEntity savedStudent = studentRepo.save(student);
			
			// Check if the save operation was successful
			if (savedStudent != null) {
				return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			// Handle the exception and return an appropriate response
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<?> addBulkStudents(List<StudentsEntity> students) {
		try {
			List<StudentsEntity> savedStudents = studentRepo.saveAll(students);

			// Check if the saveAll operation was successful
			if (!savedStudents.isEmpty()) {
				return new ResponseEntity<>("All students records are added successfully", HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>("No students records were added", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			// Handle the exception and return an appropriate response
			return new ResponseEntity<>("An error occurred while adding students records",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<List<StudentsEntity>> getAllStudent() {
		try {
			List<StudentsEntity> allStudent = studentRepo.findAll();

			if (allStudent.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(allStudent, HttpStatus.OK);
			}
		} catch (Exception e) {
			// Handle the exception and return an appropriate response
			log.info(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<StudentsEntity> getStudentById(long id) {
		try {
			Optional<StudentsEntity> student = studentRepo.findById(id);
		
			if (student.isPresent()) {
				return new ResponseEntity<>(student.get(), HttpStatus.OK);
			} else {
				throw new NoSuchElementException("Student not found");
			}
		} catch (Exception e) {
			throw new NoSuchElementException("Student not found");
		}
	}

	@Override
	public ResponseEntity<String> updateStudent(long id, StudentsEntity updatedStudentData) {
		try {
			Optional<StudentsEntity> optionalExistingStudent = studentRepo.findById(id);

			if (optionalExistingStudent.isPresent()) {
				StudentsEntity existingStudent = optionalExistingStudent.get();

				// Update the existingStudent with non-null values from updatedStudentData
//				if (updatedStudentData.getName() != null) {
//					existingStudent.setName(updatedStudentData.getName());
//				}
//
//				existingStudent.setRollNo(updatedStudentData.getRollNo());
//
//				if (updatedStudentData.getFaculty() != null) {
//					existingStudent.setFaculty(updatedStudentData.getFaculty());
//				}

				studentRepo.save(existingStudent);
				return new ResponseEntity<>("Successfully updated...", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Record not found", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			// Handle the exception and return an appropriate response
			return new ResponseEntity<>("An error occurred while updating the student record",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<String> deleteStudent(long id) {
		try {
			if (studentRepo.existsById(id)) {
				studentRepo.deleteById(id);
				return new ResponseEntity<>("Student record deleted successfully", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Student record not found", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			// Handle the exception and return an appropriate response
			return new ResponseEntity<>("An error occurred while deleting the student record",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<String> deleteStudentAll() {
		try {
			long countBeforeDeletion = studentRepo.count();

			if (countBeforeDeletion > 0) {
				studentRepo.deleteAll();
				return new ResponseEntity<>("All student records deleted successfully", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("No student records found to delete", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			// Handle the exception and return an appropriate response
			return new ResponseEntity<>("An error occurred while deleting all student records",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<StudentsEntity> getStudentDetailsByUserId() {
	    try {
	    	UsersEntity user = userController.getUserFromToken();
	        StudentsEntity student = studentRepo.getStudentDetailsByUserId(user.getId());

	        return ResponseEntity.ok(student);
	    } catch (Exception e) {
	        log.error("Error retrieving student details", e);
	        return ResponseEntity.internalServerError().build(); // Handle unexpected errors
	    }
	}

}