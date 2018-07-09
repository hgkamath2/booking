package com.booking.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.booking.entity.Vehicle;

@Repository
public interface VehicleRepository extends PagingAndSortingRepository<Vehicle,Long>{

}
