package com.booking.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.dto.CustomerRequestDTO;
import com.booking.dto.RouteInputDTO;
import com.booking.dto.RouteRequestDTO;
import com.booking.dto.RouteRequestDateDTO;
import com.booking.entity.Customer;
import com.booking.entity.Location;
import com.booking.entity.Route;
import com.booking.entity.Vehicle;
import com.booking.exception.EntityNotFoundException;
import com.booking.service.ILocationService;
import com.booking.service.IRouteService;
import com.booking.service.IVehicleService;
import com.booking.specification.RouteSpecification;
import com.booking.specification.SearchCriteria;
	

@RestController
@RequestMapping("/route")	
public class RouteController {

	@Autowired
	private IRouteService routeService;

	@Autowired
	private IVehicleService vehicleService;
	
	@Autowired
	private ILocationService locService;
	
	@GetMapping("")
    public Page<Route> getAllRoutes(Pageable page) {
        return routeService.listAllRoutes(page);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<Object> getRouteById(@PathVariable("id") Long routeId) {
		Optional<Route> route = routeService.findRouteById(routeId);
		if(!route.isPresent()) {
	        //return ResponseEntity.ok().body(new String("Customer Not Found"));
			throw new EntityNotFoundException("Route", "id", routeId);
	    }
	    return ResponseEntity.ok().body(route.get());
    }
	
	@PostMapping("/")
	public ResponseEntity<Object> addRoute(@RequestBody  RouteInputDTO routeIP) {
		
		Route route = null;
		Location locFrom = null, locTo = null;
		Vehicle vehicle = null;
		if(!locService.existsById(routeIP.getLocationFrom().getId()))
		{
			locFrom = new Location(routeIP.getLocationFrom());
			locService.addLocation(locFrom);
		}
		else
		{
			Optional<Location> locnFrom = locService.findLocationById(routeIP.getLocationFrom().getId());
			if(locnFrom.isPresent())
			  locFrom = locnFrom.get();
		}
		if(!locService.existsById(routeIP.getLocationTo().getId()))
		{
			locTo = new Location(routeIP.getLocationTo());
			locService.addLocation(locTo);
		}
		else
		{
			Optional<Location> locnTo = locService.findLocationById(routeIP.getLocationTo().getId());
			if(locnTo.isPresent())
			  locTo = locnTo.get();
		}

		if(!vehicleService.existsById(routeIP.getVehicle().getId()))
		{
			vehicle = new Vehicle(routeIP.getVehicle());
			vehicle = vehicleService.addVehicle(vehicle);
		}
		else
		{
			Optional<Vehicle> vehi = vehicleService.findVehicleById(routeIP.getVehicle().getId());
			if(vehi.isPresent())
			  vehicle = vehi.get();
		}
		route = mapRouteFromDTO(routeIP, locFrom, locTo, vehicle);
		route =  routeService.addRoute(route);
		if(route == null) {
	        return ResponseEntity.ok().body(new String("Route Not Created!"));
	    }
	    return ResponseEntity.status(HttpStatus.CREATED).body(route);
	}
	
	private Route mapRouteFromDTO(RouteInputDTO routeIP, Location locFrom, Location locTo, Vehicle vehicle)
	{
		Route route = new Route();
		route.setDistance(routeIP.getDistance());
		route.setLocationFrom(locFrom);
		route.setLocationFromId(locFrom.getId());
		route.setLocationTo(locTo);
		route.setLocationToId(locTo.getId());
		route.setVehicle(vehicle);
		route.setVehicleId(vehicle.getId());
		route.setSeatFare(routeIP.getSeatFare());
		route.setStartTime(routeIP.getStartTime());
		route.setEndTime(routeIP.getEndTime());
		return route;

	}
	
	@PostMapping("/filter")
    public ResponseEntity<Object> filterRoutesByCriteria(@RequestBody RouteRequestDTO routeDto, Sort sort) {
		List<Route> results = null;
		RouteSpecification clause1 = null;
		RouteSpecification clause2 = null;
		
		if(routeDto.getFromId() != 0)
			clause1 = 
			  new RouteSpecification(new SearchCriteria("locationFromId", ":", routeDto.getFromId()));
		if(routeDto.getToId() != 0)
			clause2 = 
			  new RouteSpecification(new SearchCriteria("locationToId", ":", routeDto.getToId()));

		if(clause1 != null && clause2 !=null)
			results = routeService.findAll(clause1.and(clause2));
		else if(clause1 != null && clause2 == null)
			results = routeService.findAll(clause1);
		else if(clause1 == null && clause2 != null)
			results = routeService.findAll(clause2);
        return ResponseEntity.ok().body(results);
    }
	
	@PostMapping("/filterDate")
    public ResponseEntity<Object> filterRoutesByDateCriteria(@RequestBody RouteRequestDateDTO routeDto, Sort sort) {
		List<Route> results = null;
		RouteSpecification clause1 = null;
		RouteSpecification clause2 = null; 
		RouteSpecification clause3 = null;
		
		if(routeDto.getFromId() != 0)
			clause1 = 
			  new RouteSpecification(new SearchCriteria("locationFromId", ":", routeDto.getFromId()));
		if(routeDto.getToId() != 0)
			clause2 = 
			  new RouteSpecification(new SearchCriteria("locationToId", ":", routeDto.getToId()));
		if(routeDto.getStartDt() != null)
			clause3 = 

			new RouteSpecification(new SearchCriteria("startTime", ">", routeDto.getStartDt()));
		results = routeService.findAll(clause1.and(clause2).and(clause3));
        return ResponseEntity.ok().body(results);
    }
	
	
	@PostMapping("/filterStDate")
    public ResponseEntity<Object> filterRoutesByStartDate(@RequestBody RouteRequestDateDTO routeDto) {
		return ResponseEntity.ok().body(routeService.filterByStDateAndFromLocation(routeDto));
    }
}
