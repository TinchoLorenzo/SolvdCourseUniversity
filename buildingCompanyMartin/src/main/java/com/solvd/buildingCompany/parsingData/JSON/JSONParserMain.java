package com.solvd.buildingCompany.parsingData.JSON;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.buildingCompany.model.location.Location;

public class JSONParserMain {

	private final static Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

	public static void main(String[] args) throws Exception {

		String jsonPath = "./src/main/resources/locations.json";
		String jsonSchemaPath = "./src/main/resources/locationSchema.json";
		
		try {
			validateJSON(jsonPath, jsonSchemaPath);
		} catch (Exception e) {
			LOGGER.error("The json is wrong" + e.getMessage());	
		}

		ObjectMapper objectMapper = new ObjectMapper();
		List<Location> locations = objectMapper.readValue(new File("src/main/resources/locations.json"), new TypeReference<List<Location>>(){});
		for(Location l: locations) {
			LOGGER.info("Location: " + l.toString());
		}
		
		String destinationPath = "./src/main/resources/locationJAXB.xml";
		objectMapper.writeValue(new File("./src/main/resources/locationJACKSON.json"), locations.get(0));
	  }
	
	private static boolean validateJSON(String jsonPath, String jsonSchemaPath) throws JSONException, FileNotFoundException {
        
	    JSONObject jsonSchema = new JSONObject(
	    	      new JSONTokener(new FileInputStream(new File(jsonSchemaPath))));
	    JSONObject jsonSubject = new JSONObject(
	      new JSONTokener(new FileInputStream(new File(jsonPath))));
	    	    
	    Schema schema = SchemaLoader.load(jsonSchema);
	    schema.validate(jsonSubject);
		
        //no exception thrown, so valid
        LOGGER.info("Document is valid");
        return true;
	}
}
