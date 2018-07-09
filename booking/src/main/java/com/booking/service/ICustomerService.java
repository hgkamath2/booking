package com.booking.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.booking.entity.Customer;

public interface ICustomerService {

	Optional<Customer> findCustomerById(long customerId) ;
	Customer addCustomer(Customer customer);
	Customer updateCustomer(Customer customer);
	Page<Customer> listAllCustomers(Pageable pageable);
	void deleteCustomer(long customerId);
	boolean existsById(long customerId);
	Optional<Customer> findCustomerByFirstNameAndLastName(String customerFN, String customerLN);
}
