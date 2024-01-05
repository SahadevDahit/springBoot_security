package com.example.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
@Entity
@Table(name="student")
public class StudentsEntity {
	   @Id
	   @Column(name = "id")
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;

	    @Column(name = "name")
	    @NotNull(message = "Name cannot be null")
	    private String name;
	    
	    @Column(name = "rollNo")
	    @NotNull(message = "rollNo cannot be null")
	    private Integer rollNo;
	    
	    @Column(name="faculty")
	    @NotNull(message = "faculty cannot be null")
	    private String faculty;
	    // Default constructor
	    public StudentsEntity() {
	        // Default constructor logic (if needed)
	    }
		public StudentsEntity(Long id, String name, Integer rollNo, String faculty) {
			super();
			this.id = id;
			this.name = name;
			this.rollNo = rollNo;
			this.faculty = faculty;
		}

		@Override
		public String toString() {
			return "studentsEntity [id=" + id + ", name=" + name + ", rollNo=" + rollNo + ", faculty=" + faculty + "]";
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getRollNo() {
			return rollNo;
		}

		public void setRollNo(Integer rollNo) {
			this.rollNo = rollNo;
		}

		public String getFaculty() {
			return faculty;
		}

		public void setFaculty(String faculty) {
			this.faculty = faculty;
		}
		public boolean containsKey(String string) {
			// TODO Auto-generated method stub
			return false;
		}
		public String get(String string) {
			// TODO Auto-generated method stub
			return null;
		}
	    
	    
	    
}
