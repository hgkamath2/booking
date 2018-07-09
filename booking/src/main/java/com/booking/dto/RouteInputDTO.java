package com.booking.dto;

import java.util.Date;

import com.booking.entity.Vehicle;


public class RouteInputDTO {
    private VehicleRequestDTO vehicle;
    private LocationRequestDTO locationFrom;
    private LocationRequestDTO locationTo;
    private int distance;
	private Date startTime;
	private Date endTime;
	private Double seatFare;
	public VehicleRequestDTO getVehicle() {
		return vehicle;
	}
	public void setVehicle(VehicleRequestDTO vehicleRequestDTO) {
		this.vehicle = vehicleRequestDTO;
	}
	public LocationRequestDTO getLocationFrom() {
		return locationFrom;
	}
	public void setLocationFrom(LocationRequestDTO locationFrom) {
		this.locationFrom = locationFrom;
	}
	public LocationRequestDTO getLocationTo() {
		return locationTo;
	}
	public void setLocationTo(LocationRequestDTO locationTo) {
		this.locationTo = locationTo;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Double getSeatFare() {
		return seatFare;
	}
	public void setSeatFare(Double seatFare) {
		this.seatFare = seatFare;
	}
	
	
}
