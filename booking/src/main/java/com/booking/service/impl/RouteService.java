package com.booking.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.booking.dto.RouteInputDTO;
import com.booking.dto.RouteRequestDTO;
import com.booking.dto.RouteRequestDateDTO;
import com.booking.entity.Location;
import com.booking.entity.Route;
import com.booking.entity.Vehicle;
import com.booking.repository.RouteRepository;
import com.booking.service.IRouteService;

@Transactional
@Service
public class RouteService implements IRouteService {

	@Autowired
	EntityManager em;
	
	@Autowired
	private RouteRepository routeRepo;

	@Override
	public Page<Route> listAllRoutes(Pageable pageable) {
		return routeRepo.findAll(pageable);
	}

	@Override
	public Optional<Route> findRouteById(long routeId) {
		return routeRepo.findById(routeId);
	}
	
	@Override
	public List<Route> findAll(Specification<Route> spec) {
		return routeRepo.findAll(spec);
	}
	
	@Override
	public boolean existsById(long routeId){
		return routeRepo.existsById(routeId);
	}

	@Override
	public Route addRoute(Route route) {
		return routeRepo.save(route);
		//return creatRoute(route);
	}
	
	public List<Route> filterByStDateAndFromLocation(RouteRequestDateDTO routeIP)
	{
		 return em.createNamedQuery("Route.getByFields", 
                Route.class).setParameter("locationFromId", routeIP.getFromId())
		 					.setParameter("locationToId", routeIP.getToId())
		 					.setParameter("startDt" , routeIP.getStartDt(), TemporalType.DATE)
                .setHint("org.hibernate.readOnly", "true").getResultList();
	}
	
	public Route creatRoute(Route routeIP)
	{
		int i = em.createNamedQuery("Route.insertQuery", 
                Route.class)
				 .setParameter(1, routeIP.getVehicleId())
		 		 .setParameter(2, routeIP.getLocationFromId())
		 		 .setParameter(3, routeIP.getLocationToId())
		 		 .setParameter(4, routeIP.getDistance())
		 		.setParameter(5, routeIP.getStartTime())
		 		.setParameter(6, routeIP.getEndTime())
		 		.setParameter(7, routeIP.getSeatFare()).executeUpdate();
           return null;
	}
	
	
}
