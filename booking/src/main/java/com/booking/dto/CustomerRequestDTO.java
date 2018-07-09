package com.booking.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class CustomerRequestDTO {

	@NotNull(message = "{customer.firstName.notNull}")
	@Size(min = 3, max = 255, message = "{customer.firstName.size}")
	private String firstName;
	
	@NotNull(message = "{customer.lastName.notNull}")
	@Size(min = 3, max = 255, message = "{customer.lastName.size}")
	private String lastName;
	
	@NotNull(message = "{customer.email.notNull}")
	@Size(min = 3, max = 255, message = "{customer.email.size}")
	private String email;

	@NotNull(message = "{customer.password.notNull}")
	@Size(min = 6, max = 60, message = "{customer.password.size}")
	private String password;
	
	@Size(min = 10, max = 20, message = "{customer.phoneNumber.size}")
	private String phoneNumber;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public CustomerRequestDTO(){}
	public CustomerRequestDTO(String firstName, String lastName, String email,String password, String phoneNumber)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = lastName;
		this.password = password;
		this.phoneNumber = phoneNumber;
	}

}
