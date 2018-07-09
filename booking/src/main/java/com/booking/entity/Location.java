package com.booking.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.booking.dto.LocationRequestDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="location")
@JsonIgnoreProperties(value = {"createdOn"}, 
allowGetters = true)
public class Location implements Serializable { 
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
    private long id;
	
	//assuming location name is unique..assuming we entering some kind of predefined codes
	@Column(name="name")
    private String name;

	@Column(name="code")
    private String code;
	
	@Column(name="created_on")	
	@CreationTimestamp
	private LocalDateTime createdOn;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}
	
	public Location(){}
	public Location(LocationRequestDTO locIP)
	{
		this.id = locIP.getId();
		this.name = locIP.getName();
		this.code = locIP.getCode();
	}
} 