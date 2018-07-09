package com.booking.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.booking.dto.PassengerRequestDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="ticket")
@JsonIgnoreProperties(value = {"createdOn", "updatedOn"}, 
allowGetters = false)
public class Ticket {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
    private long id;

    @Column(name = "reservation_id", insertable = false, updatable = false)
    private Long reservationId;
	
	@Column(name="passenger_first_name")
    private String passengerFirstName;
	
	@Column(name="passenger_last_name")
    private String passengerLastName;
	
	@Column(name="passenger_age")
    private int passengerAge;
	
	@Column(name="ticket_price")
    private double ticketPrice;
	
	@Column(name="created_on")	
	@CreationTimestamp
	private LocalDateTime createdOn;
	
	@Column(name="updated_on")	
	@UpdateTimestamp
	private LocalDateTime updatedOn;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "reservation_id", nullable = false)
    @JsonIgnore
    private Reservation reservation;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}
	
	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public Long getReservationId() {
		return reservationId;
	}

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public void setUpdatedOn(LocalDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}
	

	public Ticket(){}

	public Ticket(PassengerRequestDTO passenger, Reservation reservation, Route route) {

		this.passengerFirstName = passenger.getPassengerFirstName();
		this.passengerLastName = passenger.getPassengerLastName();
		this.passengerAge = passenger.getPassengerAge();
		this.reservation = reservation;
		this.reservationId = reservation.getId();
		this.ticketPrice = route.getSeatFare();
	}

	
}
