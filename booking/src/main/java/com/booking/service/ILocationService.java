package com.booking.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.booking.entity.Location;

public interface ILocationService {

	Optional<Location> findLocationById(long locationId) ;
	Location addLocation(Location location);
	Location updateLocation(Location location);
	Page<Location> listAllLocations(Pageable pageable);
	boolean existsById(long locationId);
}
