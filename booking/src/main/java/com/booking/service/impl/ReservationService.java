package com.booking.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.booking.entity.Reservation;
import com.booking.repository.ReservationRepository;
import com.booking.service.IReservationService;

@Transactional
@Service
public class ReservationService implements IReservationService {

	@Autowired
	private ReservationRepository reservationRepo;

	@Override
	public Page<Reservation> listAllReservations(Pageable pageable) {
		return reservationRepo.findAll(pageable);
	}

	@Override
	public Optional<Reservation> findReservationById(long reservationId) {
		return reservationRepo.findById(reservationId);
	}

	@Override
	public Reservation addReservation(Reservation reservation) {
		return reservationRepo.save(reservation);
	}

	
	@Override
	public void deleteReservation(long reservationId) {
		reservationRepo.deleteById(reservationId);
	}
	
	@Override
	public boolean existsById(long reservationId){
		return reservationRepo.existsById(reservationId);
	}

	@Override
	public Reservation updateReservationStatus(Reservation reservation) {
		return reservationRepo.save(reservation);
	}
	
	@Override
	public Reservation findByReservationNumber(String reservationNum) {
		return reservationRepo.findByReservationNumber(reservationNum);
	}

	
}
