package com.booking.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NamedQuery;

import com.booking.dto.CustomerRequestDTO;
import com.booking.dto.RouteInputDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="route")
@JsonIgnoreProperties(value = {"createdOn"}, 
allowGetters = false)


@NamedQuery(name = "Route.getByFields",
        query = "SELECT r FROM Route r where locationFromId= :locationFromId and locationToId = :locationToId and startTime > :startDt order by r.startTime"
        
)

@NamedNativeQuery(name = "Route.insertQuery",
				  query="INSERT INTO Route (vehicle_id, location_from_id, location_to_id, distance, start_time, end_time, seat_fare)"
				  		+ " values (?1, ?2, ?3, ?4, ?5, ?6, ?7)", resultClass=Route.class)

public class Route implements Serializable { 
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
    private long id;
	
    @OneToOne(optional = false, fetch = FetchType.EAGER )
    @MapsId
    private Vehicle vehicle;
	
	@Column(name = "vehicle_id", insertable = false, updatable = false)
    private long vehicleId;
	
    @OneToOne(optional = false, fetch = FetchType.EAGER )
    @MapsId
    private Location locationFrom;
    
    @Column(name = "location_from_id")
    private Long locationFromId;
    
    @OneToOne(optional = false, fetch = FetchType.EAGER )
    @MapsId
    private Location locationTo;

    @Column(name = "location_to_id")
    private Long locationToId;

	@Column(name="distance")
    private int distance;
	
	@Column(name="start_time")
	
	//@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	@Temporal(TemporalType.DATE)
	private Date startTime;
	
	@Column(name="end_time")
//	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	//@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	@Temporal(TemporalType.DATE)
	private Date endTime;
	
	@Column(name="created_on")	
	@CreationTimestamp
	private LocalDateTime createdOn;
	
	//price per ticket
	@Column(name="seat_fare")
    private double seatFare;

	//TODO mark deleted for route deletion
	//boolean isDeleted
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Location getLocationFrom() {
		return locationFrom;
	}

	public void setLocationFrom(Location locationFrom) {
		this.locationFrom = locationFrom;
	}

	public Location getLocationTo() {
		return locationTo;
	}

	public void setLocationTo(Location locationTo) {
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

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public Long getLocationFromId() {
		return locationFromId;
	}

	public void setLocationFromId(Long locationFromId) {
		this.locationFromId = locationFromId;
	}

	public Long getLocationToId() {
		return locationToId;
	}

	public void setLocationToId(Long locationToId) {
		this.locationToId = locationToId;
	}

	public double getSeatFare() {
		return seatFare;
	}

	public void setSeatFare(double seat_fare) {
		this.seatFare = seat_fare;
	}
}
