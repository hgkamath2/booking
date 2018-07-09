package com.booking.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

import com.booking.dto.CustomerRequestDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="customer")
@JsonIgnoreProperties(value = {"createdOn"}, 
allowGetters = false)
public class Customer implements Serializable { 
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
    private long id;
	
	@Column(name="first_name")
    private String firstName;
	
	@Column(name="last_name")
    private String lastName;
	
	@Column(name="email")	
	private String email;
	
	@Column(name="password")	
	private String password;
	
	
	@Column(name="phone_number")	
	private String phoneNumber;
	
	@Column(name="created_on")	
	@CreationTimestamp
	private LocalDateTime createdOn;

	public long getId() {
		return id;
	}

	public void setId(long customerId) {
		this.id = customerId;
	}

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

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	
		
	/*@Column(name="address_line1")	
	private String addressLine1;
	
	@Column(name="address_line2")	
	private String addressLine2;
	
	@Column(name="state")	
	private String state;
	
	@Column(name="country")	
	private String country;
	
	@Column(name="zip-code")	
	private String zipcode;*/

	public Customer()
	{
	}
	public Customer(CustomerRequestDTO custIP)
	{
		firstName = custIP.getFirstName();
		lastName = custIP.getLastName();
		email = custIP.getEmail();
		password = custIP.getPassword();
		phoneNumber = custIP.getPhoneNumber();
	}
	
	public Customer(Long id, String firstName, String lastName, String email,String password, String phoneNumber)
	{
		if(id != null)
			this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = lastName;
		this.password = password;
		this.phoneNumber = phoneNumber;
	}

} 