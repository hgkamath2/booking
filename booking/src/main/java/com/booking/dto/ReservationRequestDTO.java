package com.booking.dto;

import java.util.List;

import javax.persistence.Column;

public class ReservationRequestDTO extends ReserveRequestDTO{

	private Long reservationId;
	private List<PassengerRequestDTO> passengers;
	
	public List<PassengerRequestDTO> getPassengers() {
		return passengers;
	}
	public void setPassengers(List<PassengerRequestDTO> passengers) {
		this.passengers = passengers;
	}
	public Long getReservationId() {
		return reservationId;
	}
	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}

	public ReservationRequestDTO(){}
	
	public ReservationRequestDTO(Long reservationId, List<PassengerRequestDTO> passengers)
	{
		this.reservationId = reservationId;
		this.passengers = passengers;
	}
}
