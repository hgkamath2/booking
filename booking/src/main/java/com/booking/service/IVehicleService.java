package com.booking.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.booking.entity.Vehicle;

public interface IVehicleService {

	Optional<Vehicle> findVehicleById(long vehicleId) ;
	Vehicle addVehicle(Vehicle vehicle);
	Vehicle updateVehicle(Vehicle vehicle);
	Page<Vehicle> listAllVehicles(Pageable pageable);
	boolean existsById(long vehicleId);
}
