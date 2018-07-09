package com.booking.booking;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.booking.dto.CustomerRequestDTO;
import com.booking.dto.PassengerRequestDTO;
import com.booking.dto.ReservationRequestDTO;
import com.booking.dto.ReservationRespDTO;
import com.booking.dto.ReserveRequestDTO;
import com.booking.entity.Customer;
import com.booking.entity.Reservation;
import com.booking.entity.Route;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookingIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

//    @Test
//    public void createCustomer() {
//    	CustomerRequestDTO custReq = new CustomerRequestDTO("Dev1","Anand1","devanand@email.com", "devadev1", "1408122339");
//        ResponseEntity<Customer> responseEntity =
//            restTemplate.postForEntity("/customer/", custReq, Customer.class);
//        Customer cust = responseEntity.getBody();
//
//        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
//        assertEquals("Dev1", cust.getFirstName());
//    }
    
   @Test
   public void canFetchARoute() {
    	ResponseEntity<Route> route = restTemplate.exchange(
													"/route/2", 
													HttpMethod.GET,
													null,
													Route.class);
    	assertThat(route.getBody().getVehicle().getStatus(), notNullValue());
   }
//   
//   @Test
//   public void canAddAReservation() {
//	   ReserveRequestDTO newReserve = new ReserveRequestDTO(2L, 9L);
//     ResponseEntity<Reservation> responseEntity =
//         restTemplate.postForEntity("/reservation/", newReserve, Reservation.class);
//    
//
//     assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
//   }
//   
//   @Test
//   public void canAddTktsReservation() {
//	   List<PassengerRequestDTO> passengers = new ArrayList<>();
//	   passengers.add(new PassengerRequestDTO("Dana", "White", 30));
//	   passengers.add(new PassengerRequestDTO("sherlock", "White", 5));
//	   ReserveRequestDTO newReserve = new ReservationRequestDTO(12L, passengers);
//     ResponseEntity<ReservationRespDTO> responseEntity =
//         restTemplate.postForEntity("/ticket/", newReserve, ReservationRespDTO.class);
//    
//
//     assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
//   }
   
   
   
}