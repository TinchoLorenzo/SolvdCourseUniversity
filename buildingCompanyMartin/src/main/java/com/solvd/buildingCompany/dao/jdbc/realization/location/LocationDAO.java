package com.solvd.buildingCompany.dao.jdbc.realization.location;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.buildingCompany.dao.jdbc.realization.AbstractDAO;
import com.solvd.buildingCompany.dao.location.ILocationDAO;
import com.solvd.buildingCompany.model.location.City;
import com.solvd.buildingCompany.model.location.Country;
import com.solvd.buildingCompany.model.location.Location;

public class LocationDAO extends AbstractDAO implements ILocationDAO{

	private static final String SAVE_LOCATION = "INSERT INTO LOCATIONS (name, city_id) values (?,?);";
	private static final String DELETE_LOCATION= "DELETE FROM LOCATIONS WHERE id=?;";
	private static final String GET_LOCATION = "SELECT * FROM LOCATIONS WHERE id=?;";
	private static final String GET_LOCATIONS_BY_CITY= "SELECT * FROM LOCATIONS WHERE city_id=?;";
	
	private final static Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
	
	public LocationDAO() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void save(Location data) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
        try {
        	conn = cp.getConnection();
        	ps = conn.prepareStatement(SAVE_LOCATION, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,data.getName());
			ps.setLong(2,data.getCity().getId());

			int rset = ps.executeUpdate();
			if(rset==1)
				LOGGER.info("Location saved");
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
	public void update(Location data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Location getById(long id) {
		Location location=null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
        try {
        	conn = cp.getConnection();
        	ps = conn.prepareStatement(GET_LOCATION);
			ps.setLong(1,id);

			rs = ps.executeQuery();
            if(rs.next())
            {
                location = new Location( rs.getLong("id"), rs.getString("name"), null);
                CityDAO cityDAO = new CityDAO();
                location.setCity(cityDAO.getById(rs.getLong("city_id")));
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
		return location;
	}

	@Override
	public void delete(Location data) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
        try {
        	conn = cp.getConnection();
        	ps = conn.prepareStatement(DELETE_LOCATION);
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
	public List<Location> getLocationsByCityId(long id) {
		List<Location> locations=new ArrayList<Location>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
        CityDAO cityDAO = new CityDAO();
        City city = cityDAO.getById(id);
        try {
        	conn = cp.getConnection();
        	ps = conn.prepareStatement(GET_LOCATIONS_BY_CITY);
			ps.setLong(1,id);

			rs = ps.executeQuery();
            while(rs.next())
            {
                Location location = new Location( rs.getLong("id"), rs.getString("name"),null);
                location.setCity(city);
                locations.add(location);
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
		return locations;
	}

	
}
