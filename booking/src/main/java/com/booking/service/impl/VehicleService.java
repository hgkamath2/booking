package com.booking.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.booking.entity.Vehicle;
import com.booking.repository.VehicleRepository;
import com.booking.service.IVehicleService;

@Transactional
@Service
public class VehicleService implements IVehicleService {

	@Autowired
	private VehicleRepository vehicleRepo;

	@Override
	public Page<Vehicle> listAllVehicles(Pageable pageable) {
		return vehicleRepo.findAll(pageable);
	}

	@Override
	public Optional<Vehicle> findVehicleById(long vehicleId) {
		return vehicleRepo.findById(vehicleId);
	}

	@Override
	public Vehicle addVehicle(Vehicle vehicle) {
		return vehicleRepo.save(vehicle);
	}

	@Override
	public Vehicle updateVehicle(Vehicle vehicle) {
		return vehicleRepo.save(vehicle);
	}

	
	@Override
	public boolean existsById(long vehicleId)
	{
		return vehicleRepo.existsById(vehicleId);
	}
	
	
}
