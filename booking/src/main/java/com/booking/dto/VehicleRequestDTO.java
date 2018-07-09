package com.booking.dto;



public class VehicleRequestDTO {
	private Long id;
    private int totalSeats;
    private int totalBookedSeats;
    private String status = "InService";
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getTotalSeats() {
		return totalSeats;
	}
	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}
	public int getTotalBookedSeats() {
		return totalBookedSeats;
	}
	public void setTotalBookedSeats(int totalBookedSeats) {
		this.totalBookedSeats = totalBookedSeats;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}


}
