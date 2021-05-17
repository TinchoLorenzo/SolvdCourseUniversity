package com.solvd.buildingCompany.model.location;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solvd.buildingCompany.parsingData.XML.DateAdapter;

@XmlRootElement(name = "city")
@XmlAccessorType(XmlAccessType.FIELD)
public class City {

    @XmlElement(name = "id")
    @JsonProperty("id")
	private long id;
    @XmlElement(name = "name")
    @JsonProperty("name")
	private String name;
    @XmlElement(name = "abbreviation")
    @JsonProperty("abbreviation")
	private String abbreviation;
    @XmlElement(name = "zip_code")
    @JsonProperty("zip_code")
    private int zipCode;
    @XmlElement(name = "country")
    @JsonProperty("country")
	private Country country;
	@XmlElement(name = "foundation_date")
	@XmlJavaTypeAdapter(DateAdapter.class)
	@JsonProperty("foundation_date")
	private Date foundationDate;
	
	public City() {
		// TODO Auto-generated constructor stub
	}

	public City(long id, String name, String abbreviation, int zipCode, Date foundationDate, Country country) {
		super();
		this.id = id;
		this.name = name;
		this.abbreviation = abbreviation;
		this.zipCode = zipCode;
		this.foundationDate=foundationDate;
		this.country = country;
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

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Date getFoundationDate() {
		return foundationDate;
	}

	public void setFoundationDate(Date foundationDate) {
		this.foundationDate = foundationDate;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", abbreviation=" + abbreviation + ", zipCode=" + zipCode
				+ ", country=" + country + "]";
	}
	
	
	
}
