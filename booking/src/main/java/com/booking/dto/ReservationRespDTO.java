package com.booking.dto;

import java.math.BigDecimal;
import java.util.List;

import com.booking.entity.Ticket;

public class ReservationRespDTO {

	List<Ticket> tickets;
	Long reservationId;
	//BigDecimal totalAmount;
	Double totalAmount;
	public List<Ticket> getTickets() {
		return tickets;
	}
	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
	public Long getReservationId() {
		return reservationId;
	}
	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
}
