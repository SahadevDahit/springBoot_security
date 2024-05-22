package com.example.demo.services;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.demo.dao.StudentDAO;
import com.example.demo.entities.StudentsEntity;


@Service
public class StudentServiceImplementation implements StudentsService {
	
	private StudentDAO student_dao;
	 public StudentServiceImplementation(StudentDAO student_dao) {
				this.student_dao = student_dao;
	}


	@Override
	public String Home() {
		
		return student_dao.homePage();
	}

	@Override
	public  ResponseEntity<StudentsEntity> addStudent(StudentsEntity student) {
		return student_dao.addStudent(student);
		
	}
	@Override
	public ResponseEntity<?> addBulkStudents(List<StudentsEntity> students) {
		return student_dao.addBulkStudents(students);
		
	}
	
	@Override
	public ResponseEntity<List<StudentsEntity>> getAllStudent(){
		return student_dao.getAllStudent();
	}
	@Override
	public   ResponseEntity<StudentsEntity> getStudentById(long id) {
		return student_dao.getStudentById(id);
	}
	@Override
	public  ResponseEntity<String> updateStudent(long id, StudentsEntity student) {
		return student_dao.updateStudent(id, student);
	}
	@Override
	public ResponseEntity<String> deleteStudent(long id) {
		return student_dao.deleteStudent(id);
	}
	@Override
	public ResponseEntity<String> deleteStudentAll() {
		return student_dao.deleteStudentAll();
	}



}
