package com.booking.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.booking.entity.Location;
import com.booking.repository.LocationRepository;
import com.booking.service.ILocationService;


@Transactional
@Service
public class LocationService implements ILocationService {

	@Autowired
	private LocationRepository locationRepo;

	@Override
	public Page<Location> listAllLocations(Pageable pageable) {
		return locationRepo.findAll(pageable);
	}

	@Override
	public Optional<Location> findLocationById(long locationId) {
		return locationRepo.findById(locationId);
	}

	@Override
	public Location addLocation(Location location) {
		return locationRepo.save(location);
	}

	@Override
	public Location updateLocation(Location location) {
		return locationRepo.save(location);
	}

	@Override
	public boolean existsById(long locationId){
		return locationRepo.existsById(locationId);
	}
	
	
}
