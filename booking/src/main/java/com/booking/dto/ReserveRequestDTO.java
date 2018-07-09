package com.booking.dto;

public class ReserveRequestDTO {

	private Long routeId;
    private Long customerId;
    
    public Long getRouteId() {
		return routeId;
	}
	public void setRouteId(Long routeId) {
		this.routeId = routeId;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	public ReserveRequestDTO(){}
	public ReserveRequestDTO(Long routeId, Long customerId)
	{
		this.routeId = routeId;
		this.customerId = customerId;
	}

}
