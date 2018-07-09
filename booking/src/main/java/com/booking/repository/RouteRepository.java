package com.booking.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.booking.entity.Route;

@Repository
public interface RouteRepository extends PagingAndSortingRepository<Route,Long> ,JpaSpecificationExecutor<Route> {

//  class Specifications {
//    public static Specification<Route> locationFromIdIs(Long fromId) {
//      return (root, query, cb) -> cb.equal(root.get("locationFromId"), fromId);
//    }
//    
//    public Sort orderByStartTimeAsc() {
//        return new Sort(Sort.Direction.ASC, "startTime");
//      }
//  }
}