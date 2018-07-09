package com.booking.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.dto.VehicleRequestDTO;
import com.booking.entity.Vehicle;
import com.booking.exception.EntityNotFoundException;
import com.booking.service.IVehicleService;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

	@Autowired
	private IVehicleService vehicleService;
	
	@GetMapping("")
	public ResponseEntity<Object> getAllVehicles(Pageable pageable) {
		Page<Vehicle> allVehicles = vehicleService.listAllVehicles(pageable);
		return ResponseEntity.ok().body(allVehicles);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getVehicleById(@PathVariable("id") Long vehicleId) {
		Optional<Vehicle> vehicle = vehicleService.findVehicleById(vehicleId);
		if(!vehicle.isPresent()) {
	        //return ResponseEntity.ok().body(new String("Vehicle Not Found"));
			throw new EntityNotFoundException("Vehicle", "id", vehicleId);
	    }
	    return ResponseEntity.ok().body(vehicle.get());
	}
	
	@PostMapping("/")
	public ResponseEntity<Object> addVehicle(@RequestBody VehicleRequestDTO vehicleDTO) {
		Vehicle vehicle = new Vehicle(vehicleDTO);
		vehicle =  vehicleService.addVehicle(vehicle);
		if(vehicle == null) {
	        return ResponseEntity.ok().body(new String("Vehicle Not Created!"));
	    }
	    return ResponseEntity.status(HttpStatus.CREATED).body(vehicle);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateVehicle(@PathVariable("id") Long vehicleId, @RequestBody Vehicle vehicle) {
		if(!vehicleService.existsById(vehicleId))
			throw new EntityNotFoundException("Vehicle", "id", vehicleId);
		Vehicle vehicleNew = vehicleService.updateVehicle(vehicle);
		if(vehicleNew == null) {
			//need to either throw defined exceptions which will pull up string from a static resource
	        return ResponseEntity.ok().body(new String("Vehicle Not Updated!"));
	    }
	    return ResponseEntity.ok().body(vehicleNew);
	}


}
