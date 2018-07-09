package com.booking.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.booking.entity.Reservation;

@Repository
public interface ReservationRepository extends PagingAndSortingRepository<Reservation, Long>{

	Reservation findByReservationNumber(String reservationNum);
}
