package com.booking.dto;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "passenger")
public class PassengerRequestDTO {
	  private String passengerFirstName;
	  private String passengerLastName;
	   private int passengerAge;
	public String getPassengerFirstName() {
		return passengerFirstName;
	}
	public void setPassengerFirstName(String passengerFirstName) {
		this.passengerFirstName = passengerFirstName;
	}
	public String getPassengerLastName() {
		return passengerLastName;
	}
	public void setPassengerLastName(String passengerLastName) {
		this.passengerLastName = passengerLastName;
	}
	public int getPassengerAge() {
		return passengerAge;
	}
	public void setPassengerAge(int passengerAge) {
		this.passengerAge = passengerAge;
	}
	
	public PassengerRequestDTO(){}
	public  PassengerRequestDTO(String firstName, String lastName, int age)
	{
		this.passengerFirstName = firstName;
		this.passengerLastName = lastName;
		this.passengerAge = age;
	}

	   
}
