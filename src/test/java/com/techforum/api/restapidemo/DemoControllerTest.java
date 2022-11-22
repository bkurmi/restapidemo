package com.techforum.api.restapidemo;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techforum.api.restapidemo.beans.Billionair;
import com.techforum.api.restapidemo.service.DemoService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DemoControllerTest {

	@MockBean
	private DemoService demoService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@DisplayName("GET - /billionairs/{billionairId} - Id found in DB")
	void testGetBillionairByIdFound() throws Exception {
		
		//setup mocked service
		Billionair mockBillionair = new Billionair(new Long(1), "firstName", "lastName", "career");
		doReturn(mockBillionair).when(demoService).getBillionairById(1);
		
		//Execute the GET request
		mockMvc.perform(get("/billionairs/{billionairId}", 1))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName", is("firstName")));
	}
	
	@Test
	@DisplayName("GET - /billionairs/{billionairId} - Id NOT found in DB")
	void testGetBillionairByIdNotFound() throws Exception {
		
		//setup mocked service
		Billionair mockBillionair = null;
		doReturn(mockBillionair).when(demoService).getBillionairById(1);
		
		//Execute the GET request
		mockMvc.perform(get("/billionairs/{billionairId}", 1))
				.andExpect(status().is5xxServerError());
	}
	
	@Test
	@DisplayName("POST - /billionair - Billionair added successfully")
	void addBillionairSuccess() throws Exception {
		
		//setup mocked service
		Billionair postBillionair = new Billionair("firstName", "lastName", "career");
		Billionair mockBillionair = new Billionair(new Long(1), "firstName", "lastName", "career");
		doReturn(mockBillionair).when(demoService).addBillionair(any());
		
		//Execute the GET request
		mockMvc.perform(post("/billionair")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(postBillionair)))
				
				//validate response code
				.andExpect(status().isCreated());
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
}
