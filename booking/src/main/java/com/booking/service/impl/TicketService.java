package com.booking.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.booking.entity.Ticket;
import com.booking.repository.TicketRepository;
import com.booking.service.ITicketService;
import com.booking.service.ITicketService;


@Transactional
@Service
public class TicketService implements ITicketService {

	@Autowired
	private TicketRepository ticketRepo;

	@Override
	public Page<Ticket> listAllTickets(Pageable pageable) {
		return ticketRepo.findAll(pageable);
	}

	@Override
	public Optional<Ticket> findTicketById(long ticketId) {
		return ticketRepo.findById(ticketId);
	}

	@Override
	public Ticket addTicket(Ticket ticket) {
		return ticketRepo.save(ticket);
	}
	
	@Override
	public  Iterable<Ticket> addTickets(Iterable<Ticket> tickets) {
		 return ticketRepo.saveAll(tickets);
	}

	@Override
	public Ticket updateTicket(Ticket ticket) {
		return ticketRepo.save(ticket);
	}

	@Override
	public void deleteTicket(long ticketId) {
		ticketRepo.deleteById(ticketId);
	}
	
	@Override
	public boolean existsById(long ticketId)
	{
		return ticketRepo.existsById(ticketId);
	}
	
	
}
