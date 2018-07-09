package com.booking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.booking.dto.RouteRequestDateDTO;
import com.booking.entity.Customer;
import com.booking.entity.Route;

public interface IRouteService {

	Page<Route> listAllRoutes(Pageable pageable);
	Optional<Route> findRouteById(long routeId);
	List<Route> findAll(Specification<Route> spec);
	boolean existsById(long routeId);
	Route addRoute(Route route);
	
	List<Route> filterByStDateAndFromLocation(RouteRequestDateDTO routeIP);
}
