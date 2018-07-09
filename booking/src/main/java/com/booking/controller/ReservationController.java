package com.booking.controller;

import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.booking.dto.ReserveRequestDTO;
import com.booking.entity.Customer;
import com.booking.entity.Reservation;
import com.booking.exception.EntityNotFoundException;
import com.booking.service.ICustomerService;
import com.booking.service.IPaymentService;
import com.booking.service.IReservationService;
import com.booking.service.IRouteService;
import com.booking.service.ITicketService;


@RestController
@RequestMapping("/reservation")
public class ReservationController {
	@Autowired
	private IReservationService reservationService;
	
	@Autowired
	private ICustomerService customerService;
	
	@Autowired
	private IRouteService routeService;

	@Autowired
	private ITicketService ticketService;
	
	@Autowired
	@Qualifier("CreditCardService")
	private IPaymentService ccService;
	
	@Autowired
	@Qualifier("PayPalService")
	private IPaymentService ppService;;
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getReservationById(@PathVariable("id") Long reservationId) {
		Optional<Reservation> reservationTmp = reservationService.findReservationById(reservationId);
		if(!reservationTmp.isPresent()) {
	        //return ResponseEntity.ok().body(new String("Reservation Not Found"));
			throw new EntityNotFoundException("Reservation", "id", reservationId);
	    }
	    return ResponseEntity.ok().body(reservationTmp.get());
	}
	
	@GetMapping("")
	public ResponseEntity<Object> getReservationByReserveNum(@RequestParam("reserveNum") String reserveNum) {
		if(reserveNum == null || reserveNum.isEmpty()) return ResponseEntity.ok().body(new String("Reservation Number is null or empty"));
		Reservation reservationTmp = reservationService.findByReservationNumber(reserveNum);
		if(reservationTmp == null) {
			throw new EntityNotFoundException("Reservation", "reservationNumber", reserveNum);
	    }
	    return ResponseEntity.ok().body(reservationTmp);
	}
	
	/*find the route
	 * find the customer
	 * generate reservation number
	 * add reservation
	 */
	@PostMapping("/")
	public ResponseEntity<Object> addReservation(@RequestBody ReserveRequestDTO reserve) {
		
		Reservation reservation = null;
		if(!customerService.existsById(reserve.getCustomerId()))
			throw new EntityNotFoundException("Customer", "id", reserve.getCustomerId());
		if(!routeService.existsById(reserve.getRouteId()))
			throw new EntityNotFoundException("Route", "id", reserve.getRouteId());
		
		reservation = new Reservation(reserve);
		Optional<Customer> tmpCust = customerService.findCustomerById(reserve.getCustomerId());
		if(tmpCust.isPresent())
		{
			String resNum = generateUniqReservationNumber(tmpCust.get().getFirstName(), tmpCust.get().getEmail());
			reservation.setReservationNumber(resNum);
		}
		reservation =  reservationService.addReservation(reservation);
		if(reservation == null) {
	        return ResponseEntity.ok().body(new String("Reservation Not Created!"));
	    }
	    return ResponseEntity.status(HttpStatus.CREATED).body(reservation);
	}
	
	private String generateUniqReservationNumber(String firstName, String email)
	{
		StringBuilder sb = new StringBuilder();
		long milliSecTime = Instant.now().getEpochSecond();
		if(firstName != null && email != null)
		{
			sb.append(firstName.substring(0, 2))
			  .append(milliSecTime)
			  .append(email.substring(0, 2));
		}
		return sb.toString();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> cancelReservation(@PathVariable("id") Long reservationId) {
//		reservationService.deleteReservation(reservationId);
		
		//mark the reservation cancelled
		Optional<Reservation> reservationTmp = reservationService.findReservationById(reservationId);
		if(!reservationTmp.isPresent()) {
			throw new EntityNotFoundException("Reservation", "id", reservationId);
	    }
		
		Reservation reserve = reservationTmp.get();
		reserve.setStatus("CANCELLED");
		reserve = reservationService.updateReservationStatus(reserve);
	    return ResponseEntity.ok().body(reserve);
	}
	
	//assuming customer has either logged in or customer entity created as guest login
	/*
	 * User selects a route
	 * customer enters his name and payment info for pmt
	 * enters passenger info
	 * submits to reserve when this API is called.
	 *  
	 */
	@PostMapping("/purchase")
	public ResponseEntity<Object> purchaseOrder() {
		//Reservation reserve = new Reservation();
		//reserve.setReservationNumber(generateUniqReservationNumber(firstName, email));
		//Reservation reserve = reservationService.addReservation(reservationRequestDTO);
		//reservationRequestDTO.setReservationId(reserve.getId);
		//ticketService.addTickets(reservationRequestDTO);
		//use reservationId and updated the totalbooked seats and status to CONFIRM and save to DB
		//if(ccService.processPayment())
		//	return reserve with reserve number
		
		return ResponseEntity.ok().build();
	}
}