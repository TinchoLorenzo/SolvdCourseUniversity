package com.solvd.UniversityMvn.courses;

import java.text.ParseException;
import java.util.Date; 
import org.apache.commons.lang3.time.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public enum Semester {

	First2020("06/04/2020","03/07/2020", new String[] { "dd/MM/yyyy" }),
	Second2020("18/08/2020", "04/12/2020", new String[] {"dd/mm/yyyy" }),
	First2021("29/03/2021", "09/07/2021", new String[] { "	dd/MM/yyyy" }),
	Second2021("18/08/2021", "30/11/2021", new String[] { "dd/MM/yyyy" });
	private Date end;
	private Date begin;
	private Logger LOGGER;
	
	private Semester() {
		if(LOGGER==null)
			LOGGER= LogManager.getLogger(Semester.class);
	}
	
	private Semester(String begin, String end, String[] format){
		if(LOGGER==null)
			LOGGER= LogManager.getLogger(Semester.class);
		try {
			this.begin=DateUtils.parseDate(begin, format);
			this.end=DateUtils.parseDate(end, format);
		} catch (ParseException e) {
			LOGGER.error(e.getMessage());
		}
	}
	
	public Date begin() {
		return begin;
	}
	
	public Date end() {
		return end;
	}
	
}
