package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.example.demo.controller.StudentController;
import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.serivce.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


//doThrow(NumberFormatException.class).when(Integer.parseInt(s));

@WebMvcTest(StudentController.class)
public class StudentControllerSaveTest2 {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private StudentService studService;
	
	@MockBean
	private StudentRepository studRepo;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	public void testSaveStudent() throws Exception {
		
		Student s1=new Student(1,"nitin","jkc");
		Student s2=new Student(1,"nitin","jkc");
		
		when(studRepo.save(s2)).thenReturn(s1);

/*		MvcResult andReturn = mockMvc.perform(post("/students/add").contentType("application/json") 
				.content(objectMapper.writeValueAsString(s2))
				.with(csrf())
				).andExpect(status().isOk())
				.andExpect( content().string("1")).andReturn();
				
		
		String actual=andReturn.getResponse().getContentAsString();
		System.out.println("actual: "+actual);
*/
		ResultActions andDo = mockMvc.perform(post("/students/add").contentType("application/json")  
				.content(objectMapper.writeValueAsString(s2))
				.with(csrf())
				).andExpect(status().isOk())
				.andExpect( content().string("1"))
				.andDo(print());	
		
		String clgName = studRepo.save(s2).getClgName();
		
		System.out.println("college name S1: "+s1.getClgName());
		System.out.println("college name S2: "+clgName);
		
		assertEquals(s1.getClgName(), studRepo.save(s2).getClgName());
		
	}
	
	@Test
	public void notEmptyStudentTest() throws JsonProcessingException, Exception {
		Student s5=new Student();
		
		//ResultActions andDo =
				mockMvc.perform(post("/students/add")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(s5))
				.with(csrf())
				).andExpect(status().isOk())
				.andDo(print());
		
			Mockito.verify(studRepo,times(0)).save(s5);
	}
	
	@Test
	public void testDeleteStudent() throws Exception {
		int id=102;
		
	
		ResultActions perform = mockMvc.perform(delete("/students/deleteStudent/{id}",id));
		
		MvcResult andReturn = perform.andReturn();
		MockHttpServletResponse response = andReturn.getResponse();
		String contentAsString = response.getContentAsString();		
		
		int status2 = perform.andReturn().getResponse().getStatus();
		System.out.println("content as String: "+contentAsString+ " status: "+status2);
		
		assertEquals(200, status2);
	}
	
	@Test
	public void testGetStudentById() throws Exception {
		Student st=new Student(101,"as","dr");
		
		when(studService.getById(101)).thenReturn(st);
		
		ResultActions perform = mockMvc.perform(get("/students/getById/{id}",101));
		
		int status2 = perform.andReturn().getResponse().getStatus();
		String contentAsString = perform.andReturn().getResponse().getContentAsString();
		
		System.out.println("content as a  string: "+contentAsString);
		System.out.println("status: "+ status2 );
		
		assertEquals(101, studService.getById(101).getId());
		//assertEquals(101, st.getId());
	}
}