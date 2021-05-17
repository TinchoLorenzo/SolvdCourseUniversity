package com.solvd.buildingCompany.dao.jdbc.realization.location;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.buildingCompany.dao.jdbc.realization.AbstractDAO;
import com.solvd.buildingCompany.dao.location.ICityDAO;
import com.solvd.buildingCompany.model.location.City;
import com.solvd.buildingCompany.model.location.Country;

public class CityDAO extends AbstractDAO implements ICityDAO{

	private static final String SAVE_CITY = "INSERT INTO CITIES (name, abbreviation, country_id, zip_code, foundation_date) VALUES (?,?,?,?,?);";
	private static final String DELETE_CITY= "DELETE FROM CITIES WHERE id=?;";
	private static final String GET_CITY = "SELECT * FROM CITIES WHERE id=?;";
	private static final String GET_CITIES_BY_COUNTRY = "SELECT * FROM CITIES WHERE country_id=?;";
	
	private final static Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
	
	public CityDAO() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void save(City data) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
        try {
        	conn = cp.getConnection();
        	ps = conn.prepareStatement(SAVE_CITY, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,data.getName());
			ps.setString(2,data.getAbbreviation());
			ps.setLong(3,data.getCountry().getId());
			ps.setInt(4,data.getZipCode());
        	ps.setDate(5,new Date(data.getFoundationDate().getTime()));


			int rset = ps.executeUpdate();
			if(rset==1)
				LOGGER.info("City saved");
            rs = ps.getGeneratedKeys();
            if(rs.next())
            {
                data.setId(rs.getInt(1));
            }

		} catch (SQLException e) {
			LOGGER.error("SQL Exception, can not insert",e);
		} catch (InterruptedException e) {
			LOGGER.error("Cant get a connection",e);
		}finally{
			try {
				if (conn!=null) {
					cp.releaseConnection(conn);
				}
				if (ps!=null)
					ps.close();
				if (rs!=null) 
					rs.close();
			}catch (Exception e) {
				LOGGER.error("Cant close ", e.getMessage());
			}
		}
	}

	@Override
	public void update(City data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public City getById(long id) {
		City city=null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
        try {
        	conn = cp.getConnection();
        	ps = conn.prepareStatement(GET_CITY);
			ps.setLong(1,id);

			rs = ps.executeQuery();
            if(rs.next())
            {
                city = new City( rs.getLong("id"), rs.getString("name"), rs.getString("abbreviation"), rs.getInt("zip_code"),new java.util.Date(rs.getDate("foundation_date").getTime()),null);
                Country country = new Country();
                country.setId(rs.getLong("country_id"));
                city.setCountry(country);
            }	

		} catch (SQLException e) {
			LOGGER.error("SQL Exception, can not get",e);
		} catch (InterruptedException e) {
			LOGGER.error("Cant get a connection",e);
		}finally{
			try {
				if (conn!=null) {
					cp.releaseConnection(conn);
					conn.close();
				}
				if (ps!=null)
					ps.close();
				if (rs!=null) 
					rs.close();
			}catch (Exception e) {
				LOGGER.error("Cant close ", e.getMessage());
			}
		}
		return city;
	}

	@Override
	public void delete(City data) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
        try {
        	conn = cp.getConnection();
        	ps = conn.prepareStatement(DELETE_CITY);
			ps.setLong(1,data.getId());

			int rset = ps.executeUpdate();
			if(rset!=0)
				LOGGER.info("City deleted");
		} catch (SQLException e) {
			LOGGER.error("SQL Exception, can not delete",e);
		} catch (InterruptedException e) {
			LOGGER.error("Cant get a connection",e);
		}finally{
			try {
				if (conn!=null) {
					cp.releaseConnection(conn);
				}
				if (ps!=null)
					ps.close();
				if (rs!=null) 
					rs.close();
			}catch (Exception e) {
				LOGGER.error("Cant close ", e.getMessage());
			}
		}
	}

	@Override
	public List<City> getCitiesByCountryId(long id) {

		List<City> cities=new ArrayList<City>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
        CountryDAO countryDAO = new CountryDAO();
		Country country = countryDAO.getById(id);
        try {
        	conn = cp.getConnection();
        	ps = conn.prepareStatement(GET_CITIES_BY_COUNTRY);
			ps.setLong(1,id);

			rs = ps.executeQuery();
            while(rs.next())
            {
                City city = new City( rs.getLong("id"), rs.getString("name"), rs.getString("abbreviation"), rs.getInt("zip_code"),new java.util.Date(rs.getDate("foundation_date").getTime()),null);
                city.setCountry(country);
                cities.add(city);
            }

		} catch (SQLException e) {
			LOGGER.error("SQL Exception, can not get",e);
		} catch (InterruptedException e) {
			LOGGER.error("Cant get a connection",e);
		}finally{
			try {
				if (conn!=null) {
					cp.releaseConnection(conn);
					conn.close();
				}
				if (ps!=null)
					ps.close();
				if (rs!=null) 
					rs.close();
			}catch (Exception e) {
				LOGGER.error("Cant close ", e.getMessage());
			}
		}
		return cities;
	}

	
}
