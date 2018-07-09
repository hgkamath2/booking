package com.booking.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.dto.LocationRequestDTO;
import com.booking.entity.Location;
import com.booking.exception.EntityNotFoundException;
import com.booking.service.ILocationService;

@RestController
@RequestMapping("/location")	
public class LocationController {

	@Autowired
	private ILocationService locationService;
	
	@GetMapping("")
	public ResponseEntity<Object> getAllLocations(Pageable pageable) {
		Page<Location> allLocn = locationService.listAllLocations(pageable);
		return ResponseEntity.ok().body(allLocn);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getLocationById(@PathVariable("id") Long locationId) {
		Optional<Location> cust = locationService.findLocationById(locationId);
		if(!cust.isPresent()) {
			throw new EntityNotFoundException("Location", "id", locationId);
	    }
	    return ResponseEntity.ok().body(cust.get());
	}
	
	@PostMapping("/")
	public ResponseEntity<Object> addLocation(@RequestBody @Valid LocationRequestDTO locationIP) {
		Location locn = new Location(locationIP);
		
		locn =  locationService.addLocation(locn);
		if(locn == null) {
	        return ResponseEntity.ok().body(new String("Location Not Created!"));
	    }
	    return ResponseEntity.status(HttpStatus.CREATED).body(locn);
	}
}
