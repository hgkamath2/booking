package com.booking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.booking.entity.Customer;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer,Long>{

	Optional<Customer> findCustomerByFirstNameAndLastName(String customerFN, String customerLN);
}
