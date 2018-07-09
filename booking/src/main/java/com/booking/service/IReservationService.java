package com.booking.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.booking.entity.Reservation;

public interface IReservationService {

	Optional<Reservation> findReservationById(long reservationId) ;
	Reservation addReservation(Reservation reservation);
	Page<Reservation> listAllReservations(Pageable pageable);
	void deleteReservation(long reservationId);
	boolean existsById(long reservationId);
	Reservation updateReservationStatus(Reservation reservation);
	Reservation findByReservationNumber(String reservationNum);
}
