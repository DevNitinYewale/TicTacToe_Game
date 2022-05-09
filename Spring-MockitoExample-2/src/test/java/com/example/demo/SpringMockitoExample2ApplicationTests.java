package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.serivce.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class SpringMockitoExample2ApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private StudentRepository studRepo;
	
	@Autowired
	private StudentService studService;
	
	@Test
	public void notEmptyStudentTest() throws JsonProcessingException, Exception {
		//Student s5=new Student();
		String s="";
		
				mockMvc.perform(post("/students/add")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(s))
				.with(csrf())
				).andExpect(status().isBadRequest())
				.andDo(print());

	}

	@Test
	public void testCreateNewStudent() throws JsonProcessingException, Exception {
	//	Student stud1=new Student(102,"nitin","jkc");
		Student stud2=new Student(105,"nitin","jkc");
				
		MvcResult andReturn = mockMvc.perform(post("/students/add")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(stud2))
				.with(csrf()))
				.andExpect(status().isOk())
				.andDo(print())
				.andReturn();
		
		String msg=andReturn.getResponse().getContentAsString();
		
		System.out.println("msg: "+msg);
		Integer id=Integer.parseInt(msg);

		Student student=studService.getById(id);
		System.out.println("Student: "+student);
		assertThat(student.getName()).isEqualTo("nitin");
	}
	
	@Test
	public void testUpdateStudent() throws JsonProcessingException, Exception {
	
		Student stud2=new Student(101,"nitin Yewale","jkc");
				
		MvcResult andReturn = mockMvc.perform(post("/students/add")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(stud2))
				.with(csrf()))
				.andExpect(status().isOk())
				.andDo(print())
				.andReturn();
		
		String msg=andReturn.getResponse().getContentAsString();
		
		System.out.println("msg: "+msg);
		Integer id= Integer.parseInt(msg);

		Student student=studService.getById(id);
		System.out.println("Updated Student: "+student);
		assertThat(student.getName()).isEqualTo("nitin Yewale");
	}
	
}