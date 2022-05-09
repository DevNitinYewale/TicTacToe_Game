package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.serivce.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentService studService;

	
	
	@GetMapping("/show")
	public List<Student> displayAllStudent(){
		
		return studService.findAllStudent();
	}
	
	@GetMapping("/{id}")
	public Student getId1(@PathVariable Integer id) {
		return studService.getById(id);
	}
	
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<Student> getId(@PathVariable Integer id){
		Student student=studService.getById(id);
		return new ResponseEntity<Student> (student, HttpStatus.FOUND);
	}

	@PostMapping("/add")
	public String addStudent(@Validated @RequestBody Student student) {
			System.out.println("Inside AddStudent()");
			System.out.println("Id: "+student.getId()+ " Name: "+ student.getName()+ " College: "+student.getClgName());
		    studService.saveStudent(student);
		
		return String.valueOf(student.getId());
	}
	
	@DeleteMapping("/deleteStudent/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable Integer id){
				studService.deleteStudentById(id);
		
		return new ResponseEntity<>("Student deleted successfully...!", HttpStatus.OK);
	}
	
}