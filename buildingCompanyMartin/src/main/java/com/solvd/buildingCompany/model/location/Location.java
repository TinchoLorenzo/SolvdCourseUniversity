package com.solvd.buildingCompany.model.location;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement(name = "location")
@XmlAccessorType (XmlAccessType.FIELD)
public class Location {

	@XmlElement(name = "id")
	@JsonProperty("id")
	private long id;
	@XmlElement(name = "name")
	@JsonProperty("name")
	private String name;
	@XmlElement(name = "city")
	@JsonProperty("city")
	private City city;
	
	public Location() {
		// TODO Auto-generated constructor stub
	}

	public Location(long id, String name, City city) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
	}

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

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", name=" + name + ", city=" + city + "]";
	}
	
	
	
}
