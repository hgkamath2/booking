package com.booking.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.booking.dto.ReserveRequestDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="reservation")
@JsonIgnoreProperties(value = {"createdOn", "updatedOn"}, 
allowGetters = false)
public class Reservation {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
    private long id;

	@Column(name="reservation_number")
    private String reservationNumber;
	
	@Column(name = "route_id")
    private long routeId;
    
	@Column(name = "customer_id")
    private long customerId;

	@Column(name="status") //TODO enum NONE BLOCKED, BOOKED, CANCELLED
    private String status = "None";

//	@Column(name="reservation_date")
//	private LocalDate reservation_date;

	@Column(name="created_on")	
	@CreationTimestamp
	@JsonIgnore
	private LocalDateTime createdOn;
	
	@Column(name="updated_on")	
	@UpdateTimestamp
	@JsonIgnore
	private LocalDateTime UpdatedOn;
	

	@OneToMany(mappedBy = "reservationId", fetch = FetchType.LAZY, orphanRemoval = true)
	@JsonIgnore
    private Collection<Ticket> tickets = new ArrayList<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getReservationNumber() {
		return reservationNumber;
	}

	public void setReservationNumber(String reserve) {
		this.reservationNumber = reserve;
	}

	public long getRouteId() {
		return routeId;
	}

	public void setRouteId(long routeId) {
		this.routeId = routeId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public LocalDateTime getUpdatedOn() {
		return UpdatedOn;
	}

	public Collection<Ticket> getTickets() {
		return tickets;
	}
	
	public Reservation(){}
	public Reservation(ReserveRequestDTO reserve) {
		this.customerId = reserve.getCustomerId();
		this.routeId = reserve.getRouteId();
	}
}
