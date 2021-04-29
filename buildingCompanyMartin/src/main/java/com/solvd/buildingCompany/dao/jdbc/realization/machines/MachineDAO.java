package com.solvd.buildingCompany.dao.jdbc.realization.machines;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.buildingCompany.dao.jdbc.realization.AbstractDAO;
import com.solvd.buildingCompany.dao.jdbc.realization.location.CityDAO;
import com.solvd.buildingCompany.dao.machines.IMachineDAO;
import com.solvd.buildingCompany.model.location.Location;
import com.solvd.buildingCompany.model.machines.Company;
import com.solvd.buildingCompany.model.machines.Machine;
import com.solvd.buildingCompany.model.machines.MachineType;

public class MachineDAO extends AbstractDAO implements IMachineDAO{

	private static final String SAVE_MACHINE = "INSERT INTO MACHINES (buying_date, number, asset_id, type_id, company_id) values (?,?,?,?,?);";
	private static final String DELETE_MACHINE= "DELETE FROM MACHINES WHERE id=?;";
	private static final String GET_MACHINE = "SELECT * FROM MACHINES WHERE id=?;";
	
	private final static Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
	
	public MachineDAO() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void save(Machine data) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
        try {
        	conn = cp.getConnection();
        	ps = conn.prepareStatement(SAVE_MACHINE, Statement.RETURN_GENERATED_KEYS);
			ps.setDate(1,new java.sql.Date(data.getBuyingDate().getTime()));
			ps.setDouble(2,data.getNumber());
			ps.setLong(3,data.getId());
			ps.setLong(4,data.getType().getId());
			ps.setLong(5,data.getCompany().getId());

			int rset = ps.executeUpdate();
			if(rset==1)
				LOGGER.info("Machine saved");
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
	public void update(Machine data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Machine getById(long id) {
		Machine machine=null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
        try {
        	conn = cp.getConnection();
        	ps = conn.prepareStatement(GET_MACHINE);
			ps.setLong(1,id);

			rs = ps.executeQuery();
            if(rs.next())
            {
                machine = new Machine(rs.getLong("id"), new Date(rs.getDate("buying_date").getTime()), rs.getDouble("number"), MachineType.getById(rs.getLong("type_id")), null);
                Company company= new Company();
                company.setId(rs.getLong("company_id"));
                machine.setCompany(company);
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
		return machine;
	}

	@Override
	public void delete(Machine data) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
        try {
        	conn = cp.getConnection();
        	ps = conn.prepareStatement(DELETE_MACHINE);
			ps.setLong(1,data.getId());

			int rset = ps.executeUpdate();
			if(rset!=0)
				LOGGER.info("Machine deleted");
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
