package com.solvd.buildingCompany.dao.jdbc.realization.persons;

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
import com.solvd.buildingCompany.dao.jdbc.realization.location.CountryDAO;
import com.solvd.buildingCompany.dao.persons.IEmployeeDAO;
import com.solvd.buildingCompany.model.location.City;
import com.solvd.buildingCompany.model.location.Country;
import com.solvd.buildingCompany.model.machines.Company;
import com.solvd.buildingCompany.model.persons.Employee;
import com.solvd.buildingCompany.model.persons.License;

public class EmployeeDAO extends AbstractDAO implements IEmployeeDAO{

	private static final String SAVE_EMPLOYEE = "INSERT INTO EMPLOYEES (name, email, salary, asset_id, license_id) values (?,?,?,?,?);";
	private static final String DELETE_EMPLOYEE= "DELETE FROM EMPLOYEES WHERE id=?;";
	private static final String GET_EMPLOYEE = "SELECT * FROM EMPLOYEES WHERE id=?;";
	private static final String GET_EMPLOYEE_BY_LICENSE = "SELECT * FROM EMPLOYEES WHERE licence_id=?;";
	
	private final static Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
	
	public EmployeeDAO() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void save(Employee data) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
        try {
        	conn = cp.getConnection();
        	ps = conn.prepareStatement(SAVE_EMPLOYEE, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,data.getName());

			int rset = ps.executeUpdate();
			if(rset==1)
				LOGGER.info("Employee saved");
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
	public void update(Employee data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Employee getById(long id) {
		Employee employee=null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
        try {
        	conn = cp.getConnection();
        	ps = conn.prepareStatement(GET_EMPLOYEE);
			ps.setLong(1,id);

			rs = ps.executeQuery();
            if(rs.next())
            {
                employee = new Employee(rs.getString("name"), rs.getString("email"), rs.getLong("id"), rs.getDouble("salary"), null);
                License l = new License();
                l.setId(rs.getLong("licence_id"));
                employee.setLicense(l);
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
		return employee;
	}

	@Override
	public void delete(Employee data) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
        try {
        	conn = cp.getConnection();
        	ps = conn.prepareStatement(DELETE_EMPLOYEE);
			ps.setLong(1,data.getId());

			int rset = ps.executeUpdate();
			if(rset!=0)
				LOGGER.info("Employee deleted");
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
	public Employee getEmployeeByLicenseID(long id) {
		Employee employee=null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
        try {
        	conn = cp.getConnection();
        	ps = conn.prepareStatement(GET_EMPLOYEE_BY_LICENSE);
			ps.setLong(1,id);

			rs = ps.executeQuery();
            if(rs.next())
            {
            	employee = new Employee(rs.getString("name"), rs.getString("email"), rs.getLong("id"), rs.getDouble("salary"), null);
	        	License l = new License();	
	            l.setId(rs.getLong("licence_id"));
	            employee.setLicense(l);
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
		return employee;
	}

}
