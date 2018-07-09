package com.booking.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.dto.PassengerRequestDTO;
import com.booking.dto.ReservationRequestDTO;
import com.booking.dto.ReservationRespDTO;
import com.booking.entity.Reservation;
import com.booking.entity.Route;
import com.booking.entity.Ticket;
import com.booking.exception.EntityNotFoundException;
import com.booking.service.IReservationService;
import com.booking.service.IRouteService;
import com.booking.service.ITicketService;

@RestController
@RequestMapping("/ticket")
public class TicketController {
	@Autowired
	private ITicketService ticketService;
	@Autowired
	private IReservationService reservationService;
	
	@Autowired
	private IRouteService routeService;
	
	@GetMapping("")
	public ResponseEntity<Object> getAllTickets(Pageable pageable) {
		Page<Ticket> allTkts = ticketService.listAllTickets(pageable);
		return ResponseEntity.ok().body(allTkts);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getTicketById(@PathVariable("id") Long ticketId) {
		Optional<Ticket> ticketTmp = ticketService.findTicketById(ticketId);
		if(!ticketTmp.isPresent()) {
	        //return ResponseEntity.ok().body(new String("Ticket Not Found"));
			throw new EntityNotFoundException("Ticket", "id", ticketId);
	    }
	    return ResponseEntity.ok().body(ticketTmp.get());
	}

	
	@PostMapping("/v1")
	//FOR TESTING ONLY
	public ResponseEntity<Object> addTicket(@RequestBody Ticket ticket) {
		Ticket ticketTmp = null;
		if(ticket.getReservationId() != null)
		{
			Optional<Reservation> reserve = reservationService.findReservationById(ticket.getReservationId());
			if(reserve.isPresent())
			{
				Optional<Route> route = routeService.findRouteById(reserve.get().getRouteId());
				if(route.isPresent())
				{
					ticket.setTicketPrice(route.get().getSeatFare());
					ticket.setReservation(reserve.get());
					ticketTmp =  ticketService.addTicket(ticket);
					if(ticketTmp == null) {
				        return ResponseEntity.ok().body(new String("Ticket Not Created!"));
				    }
				    return ResponseEntity.status(HttpStatus.CREATED).body(ticketTmp);
				}
				return new ResponseEntity<Object>(new String("Ticket Not Created, invalid route id!"), new HttpHeaders(), HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<Object>(new String("Ticket Not Created, invalid reservation id!"), new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Object>(new String("Ticket Not Created, reservation id is null!"), new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	

	@PostMapping("/")
	public ResponseEntity<Object> addTickets(@RequestBody ReservationRequestDTO reservationReq) {
		
		List<Ticket> tkts = new ArrayList<>();
		if(reservationReq.getReservationId() != null)
		{
			Optional<Reservation> reserve = reservationService.findReservationById(reservationReq.getReservationId());
			if(reserve.isPresent())
			{
				Optional<Route> route = routeService.findRouteById(reserve.get().getRouteId());
				if(route.isPresent())
				{
					for(PassengerRequestDTO passenger :reservationReq.getPassengers())
					{
						Ticket tkt = new Ticket(passenger, reserve.get(), route.get());
						tkts.add(tkt);
					}
					List<Ticket> respTkts = new ArrayList<>();
					ticketService.addTickets(tkts).forEach(respTkts::add);
					ReservationRespDTO respDTO = new ReservationRespDTO();
					respDTO.setTickets(respTkts);
					respDTO.setReservationId(reservationReq.getReservationId());
					respDTO.setTotalAmount(route.get().getSeatFare()*reservationReq.getPassengers().size());
					return ResponseEntity.status(HttpStatus.CREATED).body(respDTO);
				}
				return new ResponseEntity<Object>(new String("Ticket Not Created, invalid route id!"), new HttpHeaders(), HttpStatus.BAD_REQUEST);
			}
			else
				return new ResponseEntity<Object>(new String("Ticket Not Created, invalid reservation id!"), new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Object>(new String("Ticket Not Created, reservation id is null!"), new HttpHeaders(), HttpStatus.BAD_REQUEST);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateTicket(@PathVariable("id") Long ticketId, @RequestBody Ticket ticket) {
		if(!ticketService.existsById(ticketId))
			throw new EntityNotFoundException("Ticket", "id", ticketId);
		Ticket ticketTmp = ticketService.updateTicket(ticket);
		if(ticketTmp == null) {
			//need to either throw defined exceptions which will pull up string from a static resource
	        return ResponseEntity.ok().body(new String("Ticket Not Updated!"));
	    }
	    return ResponseEntity.ok().body(ticketTmp);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteTicket(@PathVariable("id") long ticketId) {
		Optional<Ticket> tkt = ticketService.findTicketById(ticketId);
		if(tkt == null || !tkt.isPresent())
			throw new EntityNotFoundException("Ticket", "id", ticketId);
		Reservation reservation = tkt.get().getReservation();
		if(reservation != null)
		{
			if(reservation.getTickets().size() == 1)
				reservationService.deleteReservation(tkt.get().getReservationId());
		}
		ticketService.deleteTicket(ticketId);
	    return ResponseEntity.ok().build();
	}
}
