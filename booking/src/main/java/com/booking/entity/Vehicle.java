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

import com.booking.dto.VehicleRequestDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="vehicle")
@JsonIgnoreProperties(value = {"createdOn"}, 
allowGetters = true)
public class Vehicle implements Serializable { 
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
    private long id;
	
	//TODO future seat should be a separate entity with seatnumber and seatBooking 
	@Column(name="total_seats")
    private int totalSeats;
	
	@Column(name="total_booked_seats")
    private int totalBookedSeats;

	@Column(name="status") //TODO enum InService, NotInService
    private String status = "InService";

	@Column(name="created_on")	
	@CreationTimestamp
	private LocalDateTime createdOn;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public int getTotalBookedSeats() {
		return totalBookedSeats;
	}

	public void setTotalBookedSeats(int totalBookedSeats) {
		this.totalBookedSeats = totalBookedSeats;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}
	
	public Vehicle(){}
	public Vehicle(VehicleRequestDTO vehicleIP)
	{
		totalBookedSeats = vehicleIP.getTotalBookedSeats();
		totalSeats = vehicleIP.getTotalSeats();
		status = vehicleIP.getStatus();
	}
	
}
