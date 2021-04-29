package com.solvd.buildingCompany.dao.jdbc.realization.persons;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.buildingCompany.dao.jdbc.realization.AbstractDAO;
import com.solvd.buildingCompany.dao.persons.ILicenseDAO;
import com.solvd.buildingCompany.dao.projects.IPaymentDAO;
import com.solvd.buildingCompany.model.machines.Company;
import com.solvd.buildingCompany.model.persons.License;

public class LicenseDAO extends AbstractDAO implements ILicenseDAO{

	private static final String SAVE_LICENSE = "INSERT INTO LICENSES (number, exp_date) values (?,?);";
	private static final String DELETE_LICENSE= "DELETE FROM LICENSES WHERE id=?;";
	private static final String GET_LICENSE = "SELECT * FROM LICENSES WHERE id=?;";
	
	private final static Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
	
	public LicenseDAO() {
	}
	
	@Override
	public void save(License data) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
        try {
        	conn = cp.getConnection();
        	ps = conn.prepareStatement(SAVE_LICENSE, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,data.getNumber());
			ps.setDate(2,new Date(data.getExpDate().getTime()));
			int rset = ps.executeUpdate();
			if(rset==1)
				LOGGER.info("License saved");
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
	public void update(License data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public License getById(long id) {
		License license=null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
        try {
        	conn = cp.getConnection();
        	ps = conn.prepareStatement(GET_LICENSE);
			ps.setLong(1,id);

			rs = ps.executeQuery();
            if(rs.next())
            {
                license= new License( rs.getLong("id"), rs.getString("number"), rs.getDate("exp_date"));
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
		return license;
	}

	@Override
	public void delete(License data) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
        try {
        	conn = cp.getConnection();
        	ps = conn.prepareStatement(DELETE_LICENSE);
			ps.setLong(1,data.getId());

			int rset = ps.executeUpdate();
			if(rset!=0)
				LOGGER.info("License deleted");
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
