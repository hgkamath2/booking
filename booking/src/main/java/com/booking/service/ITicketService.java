package com.booking.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.booking.entity.Ticket;

public interface ITicketService {

	Optional<Ticket> findTicketById(long ticketId) ;
	Ticket addTicket(Ticket ticket);
	Ticket updateTicket(Ticket ticket);
	Page<Ticket> listAllTickets(Pageable pageable);
	void deleteTicket(long ticketId);
	boolean existsById(long ticketId);
	Iterable<Ticket> addTickets(Iterable<Ticket> tickets);
}
