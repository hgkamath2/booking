package com.booking.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.booking.dto.CustomerRequestDTO;
import com.booking.entity.Customer;
import com.booking.exception.EntityNotFoundException;
import com.booking.service.ICustomerService;

@RestController
@RequestMapping("/customer")	
public class CustomerController {

	@Autowired
	private ICustomerService customerService;
	
	@GetMapping("")
	public ResponseEntity<Object> getAllCustomers(Pageable pageable) {
		Page<Customer> allCust = customerService.listAllCustomers(pageable);
		return ResponseEntity.ok().body(allCust);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getCustomerById(@PathVariable("id") long id) {
		Optional<Customer> cust = customerService.findCustomerById(id);
		if(!cust.isPresent()) {
			throw new EntityNotFoundException("Customer", "id", id);
	    }
	    return ResponseEntity.ok().body(cust.get());
	}
	
	@GetMapping("/name")
	public ResponseEntity<Object> getCustomerByName(@RequestParam("firstname") String firstName,
												 @RequestParam("lastname") String lastName) {
		Optional<Customer> cust = customerService.findCustomerByFirstNameAndLastName(firstName, lastName);
		if(!cust.isPresent()) {
			throw new EntityNotFoundException("Customer", "firstName" + " lastName", firstName +" " + lastName);
	    }
	    return ResponseEntity.ok().body(cust);
	}
	
	@PostMapping("/")
	public ResponseEntity<Object> addCustomer(@RequestBody @Valid CustomerRequestDTO customerIP) {
		Customer cust = new Customer(customerIP);
		cust =  customerService.addCustomer(cust);
		if(cust == null) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
	        		body(new String("Customer Not Created!"));
	    }
	    return ResponseEntity.status(HttpStatus.CREATED).body(cust);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateCustomer(@PathVariable("id") Long customerId, @RequestBody @Valid CustomerRequestDTO customerIP) {
		if(!customerService.existsById(customerId))
			throw new EntityNotFoundException("Customer", "id", customerId);
		Customer cust = new Customer(customerIP);
		cust = customerService.updateCustomer(cust);
		if(cust == null) {
			//need to either throw defined exceptions which will pull up string from a static resource
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new String("Customer Not Updated!"));
	    }
	    return ResponseEntity.ok().body(cust);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteCustomer(@PathVariable("id") Long customerId) {
		customerService.deleteCustomer(customerId);
	    return ResponseEntity.ok().build();
	}
}

