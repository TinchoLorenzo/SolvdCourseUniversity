package com.solvd.buildingCompany.dao.jdbc.realization.location;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.buildingCompany.dao.jdbc.realization.AbstractDAO;
import com.solvd.buildingCompany.dao.jdbc.realization.persons.ClientDAO;
import com.solvd.buildingCompany.dao.location.ICountryDAO;
import com.solvd.buildingCompany.model.location.Country;
import com.solvd.buildingCompany.model.persons.Client;

public class CountryDAO extends AbstractDAO implements ICountryDAO{

	private static final String SAVE_COUNTRY = "INSERT INTO COUNTRY(name, abbreviation) VALUES (?,?);";
	private static final String DELETE_COUNTRY = "DELETE FROM COUNTRY WHERE id=?;";
	private static final String GET_COUNTRY = "SELECT * FROM COUNTRY WHERE id=?;";
	
	private final static Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

	public CountryDAO() {
		// TODO Auto-generated constructor stub
	}
		
	@Override
	public void save(Country data) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
        try {
        	conn = cp.getConnection();
        	ps = conn.prepareStatement(SAVE_COUNTRY, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,data.getName());
			ps.setString(2,data.getAbbreviation());

			int rset = ps.executeUpdate();
			if(rset==1)
				LOGGER.info("Country saved");
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
	public void update(Country data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Country getById(long id) {
		Country country=null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
        try {
        	conn = cp.getConnection();
        	ps = conn.prepareStatement(GET_COUNTRY, Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1,id);

            rs = ps.executeQuery();
            if(rs.next())
            {
                country = new Country( rs.getLong("id"), rs.getString("name"), rs.getString("abbreviation"));
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
		return country;
	}

	@Override
	public void delete(Country data) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
        try {
        	conn = cp.getConnection();
        	ps = conn.prepareStatement(DELETE_COUNTRY);
			ps.setLong(1,data.getId());

			int rset = ps.executeUpdate();
			if(rset!=0)
				LOGGER.info("Country deleted");
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

}
