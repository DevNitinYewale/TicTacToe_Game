package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.demo.controller.StudentController;
import com.example.demo.entity.Student;
import com.example.demo.serivce.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectMapper;


//@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
public class StudentControllerTest1 {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private StudentService studService;
	
	//TEST ALL STUDENT
	@Test
	public void testAllStudent() throws Exception {
		List<Student> student=new ArrayList<>();
		student.add(new Student(102,"abc","xyz"));
		student.add(new Student(103,"erd","rtg"));
		student.add(new Student(104,"fgh","ert"));
		
		when(studService.findAllStudent()).thenReturn(student);
	
		String url="/students/show";
		
		//mockMvc.perform(get(url)).andExpect(status().isOk());
		
		MvcResult andReturn = mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();
		System.out.println(	"andReturn: "+andReturn);
		
		String contentAsString = andReturn.getResponse().getContentAsString();
		System.out.println(contentAsString);
		assertEquals("abc", studService.findAllStudent().get(0).getName());
		
	}
}
