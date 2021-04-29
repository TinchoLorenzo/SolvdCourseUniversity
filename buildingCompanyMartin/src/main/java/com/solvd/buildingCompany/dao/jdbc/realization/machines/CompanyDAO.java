package com.solvd.buildingCompany.dao.jdbc.realization.machines;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.buildingCompany.dao.jdbc.realization.AbstractDAO;
import com.solvd.buildingCompany.dao.jdbc.realization.location.CityDAO;
import com.solvd.buildingCompany.dao.machines.ICompanyDAO;
import com.solvd.buildingCompany.model.location.Location;
import com.solvd.buildingCompany.model.machines.Company;

public class CompanyDAO extends AbstractDAO implements ICompanyDAO{

	private static final String SAVE_COMPANY = "INSERT INTO COMPANIES (name) values (?);";
	private static final String DELETE_COMPANY= "DELETE FROM COMPANIES WHERE id=?;";
	private static final String GET_COMPANY = "SELECT * FROM COMPANIES WHERE id=?;";
	
	private final static Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
	
	public CompanyDAO() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void save(Company data) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
        try {
        	conn = cp.getConnection();
        	ps = conn.prepareStatement(SAVE_COMPANY, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,data.getName());

			int rset = ps.executeUpdate();
			if(rset==1)
				LOGGER.info("Company saved");
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
	public void update(Company data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Company getById(long id) {
		Company company=null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
        try {
        	conn = cp.getConnection();
        	ps = conn.prepareStatement(GET_COMPANY);
			ps.setLong(1,id);

			rs = ps.executeQuery();
            if(rs.next())
            {
                company = new Company( rs.getLong("id"), rs.getString("name"));
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
		return company;
	}

	@Override
	public void delete(Company data) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
        try {
        	conn = cp.getConnection();
        	ps = conn.prepareStatement(DELETE_COMPANY);
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

}
