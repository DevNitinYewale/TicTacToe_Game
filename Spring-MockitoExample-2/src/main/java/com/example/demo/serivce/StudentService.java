package com.example.demo.serivce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studRepo;
	
	public List<Student> findAllStudent(){
		
		return studRepo.findAll();
	}
	
	public Student getById(int id) {
		
		return studRepo.findById(id).get();
	}
	
	public void saveStudent(Student student) {
		studRepo.save(student);
	}
	
	public void deleteStudentById(Integer id) {
		studRepo.deleteById(id);
	}
	
}