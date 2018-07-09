package com.booking.booking;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.booking.controller.CustomerController;
import com.booking.dto.CustomerRequestDTO;
import com.booking.entity.Customer;
import com.booking.exception.EntityNotFoundException;
import com.booking.service.ICustomerService;
import com.booking.service.impl.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerMockMvcTest {
	
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerService customerService;
	private JacksonTester<Customer> jsonCustomer;

	@MockBean
	private CustomerController custController;
	
    @Before
    public void setup() {
        // Initializes the JacksonTester
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void canFetchCustomerByIdWhenExists() throws Exception {
    	
		// given using service
		//when(customerService.findCustomerById(5))
		//.thenReturn(Optional.of(new Customer((long)5,"Dev","Anand","devanand@email.com", "blahblah", "1408123356")));
		
		  Customer cust = new Customer((long)5,"Dev","Anand","devanand@email.com", "blahblah", "1408123356");
		  
		//when using controller
		when(custController.getCustomerById(5))
		  	.thenReturn(ResponseEntity.ok().body(cust));
        
        MockHttpServletResponse response = mvc.perform(
                get("/customer/5")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonCustomer.write(new Customer((long)5, "Dev","Anand","devanand@email.com", "blahblah", "1408123356")).getJson()
        );
    }

    @Test
    public void canFetchCustomerByIdWhenDoesNotExist() throws Exception {
        // given
    	given(custController.getCustomerById(5))
                .willThrow(new EntityNotFoundException("Customer", "id", 10));
 
        // when
        MockHttpServletResponse response = mvc.perform(
                get("/customer/5")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
 
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(response.getContentAsString()).isEmpty();
    }
    
    @Test
    public void canCreateANewCustomer() throws Exception {
    	//when
    	CustomerRequestDTO custReq = new CustomerRequestDTO("Dev1","Anand1","devanand@email.com", "devadev1", "1408122339");
    	Customer cust = new Customer(custReq);
    	mvc.perform(
                post("/customer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonCustomer.write(cust).getJson()))
               .andDo(print())
               .andExpect(status().isOk());
    }

}