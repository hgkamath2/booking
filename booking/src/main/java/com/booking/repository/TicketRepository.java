package com.booking.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.booking.entity.Ticket;

@Repository
public interface TicketRepository extends PagingAndSortingRepository<Ticket,Long>{

}
