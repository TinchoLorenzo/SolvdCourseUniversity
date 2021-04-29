package com.solvd.buildingCompany.dao.jdbc.realization.projects;

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
import com.solvd.buildingCompany.dao.jdbc.realization.location.LocationDAO;
import com.solvd.buildingCompany.dao.projects.IProjectDAO;
import com.solvd.buildingCompany.model.location.Location;
import com.solvd.buildingCompany.model.machines.Company;
import com.solvd.buildingCompany.model.projects.Asset;
import com.solvd.buildingCompany.model.projects.Contract;
import com.solvd.buildingCompany.model.projects.Payment;
import com.solvd.buildingCompany.model.projects.Project;

public class ProjectDAO extends AbstractDAO implements IProjectDAO{

	private static final String SAVE_PROJECT = "INSERT INTO PROJECTS (begin_date, description, expected_end_date, location_id) values (?,?,?,?);";
	private static final String DELETE_PROJECT= "DELETE FROM PROJECTS WHERE id=?;";
	private static final String GET_PROJECT = "SELECT * FROM PROJECTS WHERE id=?;";
	private static final String GET_PROJECT_BY_LOCATION= "SELECT * FROM PROJECTS WHERE location_id=?;";
	
	private final static Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
	
	
	public ProjectDAO() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void save(Project data) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
        try {
        	conn = cp.getConnection();
        	ps = conn.prepareStatement(SAVE_PROJECT, Statement.RETURN_GENERATED_KEYS);
        	ps.setDate(1,new Date(data.getBeginDate().getTime()));
        	ps.setString(2, data.getDescription());
        	if(data.getExpectedEndDate()==null)
        		ps.setNull(3,  java.sql.Types.DATE);
        	else
        		ps.setDate(3,new Date(data.getBeginDate().getTime()));
        	ps.setLong(4, data.getLocation().getId());
			int rset = ps.executeUpdate();
			if(rset==1)
				LOGGER.info("Project saved");
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
	public void update(Project data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Project getById(long id) {
		Project project=null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
        try {
        	conn = cp.getConnection();
        	ps = conn.prepareStatement(GET_PROJECT);
			ps.setLong(1,id);

			rs = ps.executeQuery();
            if(rs.next())
            {
            	java.util.Date expectedEndDate = null;
            	if( rs.getDate("expected_end_date")!=null)
            		expectedEndDate = new java.util.Date(rs.getDate("expected_end_date").getTime());
            	project = new Project(rs.getLong("id"), new java.util.Date(rs.getDate("begin_date").getTime()), rs.getString("description"),
            			expectedEndDate, null, null);
            	Location l = new Location();
            	l.setId(rs.getLong("location_id"));
            	project.setLocation(l);
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
		return project;
	}

	@Override
	public void delete(Project data) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
        try {
        	conn = cp.getConnection();
        	ps = conn.prepareStatement(DELETE_PROJECT);
			ps.setLong(1,data.getId());

			int rset = ps.executeUpdate();
			if(rset!=0)
				LOGGER.info("Project deleted");
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
	public List<Project> getProjectByLocationId(long id) {
		List<Project> projects=new ArrayList<Project>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		LocationDAO ld = new LocationDAO();
		Location location = ld.getById(id);
        try {
        	conn = cp.getConnection();
        	ps = conn.prepareStatement(GET_PROJECT_BY_LOCATION);
			ps.setLong(1,id);

			rs = ps.executeQuery();
            while(rs.next())
            {
            	java.util.Date expectedEndDate = null;
            	if( rs.getDate("expected_end_date")!=null)
            		expectedEndDate = new java.util.Date(rs.getDate("expected_end_date").getTime());
            	Project project = new Project(rs.getLong("id"), new java.util.Date(rs.getDate("begin_date").getTime()), rs.getString("description"),
            			expectedEndDate, null, null);
            	project.setLocation(location);
                projects.add(project);
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
		return projects;
	}

}
