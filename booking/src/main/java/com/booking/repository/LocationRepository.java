package com.booking.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.booking.entity.Location;

@Repository
public interface LocationRepository extends PagingAndSortingRepository<Location,Long>{

}
