package com.solvd.buildingCompany.parsingData.XML;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.invoke.MethodHandles;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.buildingCompany.model.location.City;
import com.solvd.buildingCompany.model.location.Country;
import com.solvd.buildingCompany.model.location.Location;

public class LocationXMLParser {

	private final static Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

	public LocationXMLParser() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Location> parseLocation(String filename) throws FileNotFoundException, XMLStreamException {
		XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
		XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(filename)); 
		List<Location> locations = new ArrayList<Location>();
		
		Location location = new Location();
		while (reader.hasNext()) {
		    XMLEvent nextEvent = reader.nextEvent();
		    if (nextEvent.isStartElement()) {
		        StartElement startElement = nextEvent.asStartElement();
		        switch (startElement.getName().getLocalPart()) {
		            case "location":
		                location = new Location();
		                break;
		            case "name":
		                nextEvent = reader.nextEvent();
		                location.setName(nextEvent.asCharacters().getData());
		                break;
		            case "id":
		                nextEvent = reader.nextEvent();
		                location.setId(Long.valueOf(nextEvent.asCharacters().getData()));
		                break;
		            case "city":
		                location.setCity(this.parseCity(reader, nextEvent));
		                break;
		            default:
		            	break;
		        }
		    }
		    if (nextEvent.isEndElement()) {
		        EndElement endElement = nextEvent.asEndElement();
		        if (endElement.getName().getLocalPart().equals("location")) {
		            locations.add(location);
		        }
		    }
		}
	    reader.close();

	    return locations;
	}
    
	private City parseCity(XMLEventReader reader, XMLEvent nextEvent) throws XMLStreamException {
		
		City city = new City();
		while (reader.hasNext()) {
		    if (nextEvent.isStartElement()) {
		        StartElement startElement = nextEvent.asStartElement();
		        switch (startElement.getName().getLocalPart()) {
		            case "city":
		                //Skip as the city has been initialized and can have only one
		            	nextEvent = reader.nextEvent();
		            	city = new City();
		                break;
		            case "name":
		                nextEvent = reader.nextEvent();
		                city.setName(nextEvent.asCharacters().getData());
		                break;
		            case "id":
		                nextEvent = reader.nextEvent();
		                city.setId(Long.valueOf(nextEvent.asCharacters().getData()));
		                break;
		            case "abbreviation":
		                nextEvent = reader.nextEvent();
		                city.setAbbreviation(nextEvent.asCharacters().getData());
		                break;
		            case "zip_code":
		                nextEvent = reader.nextEvent();
		                city.setZipCode(Integer.valueOf(nextEvent.asCharacters().getData()));
		                break;
		            case "foundation_date":
		                nextEvent = reader.nextEvent();
						try {
							city.setFoundationDate(new SimpleDateFormat("MM/dd/yyyy").parse(nextEvent.asCharacters().getData()));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							LOGGER.error("The city foundation date format is invalid");
						}
		                break;
		            case "country":
		                city.setCountry(this.parseCountry(reader, nextEvent));
		                break;
		            default:
		            	nextEvent = reader.nextEvent();
		            	break;
		        }
		    }
		    if (nextEvent.isEndElement()) {
		        EndElement endElement = nextEvent.asEndElement();
		        if (endElement.getName().getLocalPart().equals("city")) {
		            return city;
		        }
		    }
		    nextEvent = reader.nextEvent();
		}
		
		return null;
	}
	
	private Country parseCountry(XMLEventReader reader, XMLEvent nextEvent) throws XMLStreamException {
		
		Country country = new Country();
		while (reader.hasNext()) {
		    if (nextEvent.isStartElement()) {
		        StartElement startElement = nextEvent.asStartElement();
		        switch (startElement.getName().getLocalPart()) {
		            case "country":
		                //Skip as the country has been initialized and can have only one
		            	nextEvent = reader.nextEvent();
		                break;
		            case "name":
		                nextEvent = reader.nextEvent();
		                country.setName(nextEvent.asCharacters().getData());
		                break;
		            case "id":
		                nextEvent = reader.nextEvent();
		                country.setId(Long.valueOf(nextEvent.asCharacters().getData()));
		                break;
		            case "abbreviation":
		                nextEvent = reader.nextEvent();
		                country.setAbbreviation(nextEvent.asCharacters().getData());
		                break;
		            default:
		            	nextEvent = reader.nextEvent();
		            	break;
		        }
		    }
		    if (nextEvent.isEndElement()) {
		        EndElement endElement = nextEvent.asEndElement();
		        if (endElement.getName().getLocalPart().equals("country")) {
		            return country;
		        }
		    }
		    nextEvent = reader.nextEvent();
		}
		
		return null;
	}
}
