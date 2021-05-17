package com.solvd.buildingCompany.model.location;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement(name = "country")
@XmlAccessorType (XmlAccessType.FIELD)
public class Country {

	@XmlElement(name = "id")
	@JsonProperty("id")
	private long id;
	@XmlElement(name = "name")
	@JsonProperty("name")
	private String name;
	@XmlElement(name = "abbreviation")
	@JsonProperty("abbreviation")
	private String abbreviation;
	
	public Country() {
		// TODO Auto-generated constructor stub
	}

	public Country(long id, String name, String abbreviation) {
		super();
		this.id = id;
		this.name = name;
		this.abbreviation = abbreviation;
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

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	@Override
	public String toString() {
		return "Country [id=" + id + ", name=" + name + ", abbreviation=" + abbreviation + "]";
	}
	
	
}
