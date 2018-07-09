package com.booking.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.booking.entity.Customer;
import com.booking.repository.CustomerRepository;
import com.booking.service.ICustomerService;

@Transactional
@Service
public class CustomerService implements ICustomerService {

	@Autowired
	private CustomerRepository customerRepo;

	@Override
	public Page<Customer> listAllCustomers(Pageable pageable) {
		return customerRepo.findAll(pageable);
	}

	@Override
	public Optional<Customer> findCustomerById(long customerId) {
		return customerRepo.findById(customerId);
	}

	@Override
	public Customer addCustomer(Customer customer) {
		return customerRepo.save(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		return customerRepo.save(customer);
	}

	@Override
	public void deleteCustomer(long customerId) {
		customerRepo.deleteById(customerId);
	}
	
	@Override
	public boolean existsById(long customerId){
		return customerRepo.existsById(customerId);
	}

	@Override
	public Optional<Customer> findCustomerByFirstNameAndLastName(String customerFN, String customerLN) {
		return customerRepo.findCustomerByFirstNameAndLastName(customerFN, customerLN);
	}
	
	
}
