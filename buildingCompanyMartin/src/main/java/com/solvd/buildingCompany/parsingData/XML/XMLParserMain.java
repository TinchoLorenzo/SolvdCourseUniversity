package com.solvd.buildingCompany.parsingData.XML;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stax.StAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import com.solvd.buildingCompany.model.location.Location;

public class XMLParserMain {

	private final static Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

	public static void main(String[] args) throws Exception {

		String xmlPath = "./src/main/resources/locations.xml";
		String xsdPath = "./src/main/resources/locations.xsd";
		try {
			validateXML(xmlPath, xsdPath);
		} catch (Exception e) {
			LOGGER.error("The xml is wrong");	
		}

		LocationXMLParser locationParser = new LocationXMLParser();
		List<Location> locations = locationParser.parseLocation(xmlPath);
		
		for(Location l: locations) {
			LOGGER.info("Location: " + l.toString());
		}
		
		
		String destinationPath = "./src/main/resources/locationJAXB.xml";
		marshallLocation(locations.get(0), destinationPath);
		
		Location l = unmarshalLocation(destinationPath);
		
	  }
	
	private static boolean validateXML(String xmlPath, String xsdPath) throws XMLStreamException, FactoryConfigurationError, IOException, SAXException {
        
		XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(new FileInputStream(xmlPath));

        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = factory.newSchema(new File(xsdPath));

        Validator validator = schema.newValidator();
        validator.validate(new StAXSource(reader));

        //no exception thrown, so valid
        LOGGER.info("Document is valid");
        reader.close();
        return true;
	}
	
	private static void marshallLocation(Location l, String destinationPath) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(Location.class);
	    Marshaller mar= context.createMarshaller();
	    mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	    mar.marshal(l, new File(destinationPath));
	    
	}
	
	private static Location unmarshalLocation(String path) throws JAXBException, FileNotFoundException {
	    JAXBContext context = JAXBContext.newInstance(Location.class);
	    return (Location) context.createUnmarshaller()
	      .unmarshal(new FileReader(path));
	}
}
