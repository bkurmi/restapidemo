package com.techforum.api.restapidemo;

import static org.mockito.Mockito.doReturn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.techforum.api.restapidemo.beans.Billionair;
import com.techforum.api.restapidemo.repository.DemoRepository;
import com.techforum.api.restapidemo.service.DemoService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DemoServiceTest {

	@Autowired
	DemoService demoService;
	
	@MockBean
	private DemoRepository demoRepository;
	
	@Test
	@DisplayName("GET - getBillionairById Success - Billionair got from provided ID")
	void getBillionairByIdSuccessTest() {
		
		//setup mock
		Billionair mockBillionair = new Billionair(new Long(1), "firstName", "lastName", "career");
		doReturn(mockBillionair).when(demoRepository).getBillionairById(1);
		
		//execute service call
		Billionair returnedBillionair = demoService.getBillionairById(1);
		
		//Assert the response
		Assertions.assertTrue(returnedBillionair!= null, "Billionair found");
		
	}
	
	@Test
	@DisplayName("DELETE - deleteBillionair - Billionair deleted successfully!")
	void deleteBillionairSuccessTest() {
		
		//setup mock
		doReturn(true).when(demoRepository).deleteBillionair(1);
		
		//execute service call
		Boolean deletionStatus = demoService.deleteBillionair(1);
		
		//Assert the response
		Assertions.assertTrue(deletionStatus == true, "Billionair deleted successfully");
		
	}
	
	@Test
	@DisplayName("GET - getBillionairById FAILURE - Billionair NOT recieved for provided ID")
	void getBillionairByIdFailedTest() {
		
		//setup mock
		Billionair mockBillionair = new Billionair(new Long(1), "firstName", "lastName", "career");
		doReturn(mockBillionair).when(demoRepository).getBillionairById(1);
		
		//execute service call
		Billionair returnedBillionair = demoService.getBillionairById(3);
		
		//Assert the response
		Assertions.assertTrue(returnedBillionair== null, "Billionair NOT found");
		
	}
	
}
