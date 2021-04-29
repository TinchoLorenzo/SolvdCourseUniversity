package com.solvd.buildingCompany.dao.jdbc.realization.persons;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.buildingCompany.connections.ConnectionPool;
import com.solvd.buildingCompany.dao.jdbc.realization.AbstractDAO;
import com.solvd.buildingCompany.dao.persons.IClientDAO;
import com.solvd.buildingCompany.model.persons.Client;

public class ClientDAO extends AbstractDAO implements IClientDAO{

	private static final String SAVE_CLIENT = "INSERT INTO CLIENTS(name, email) VALUES (?,?);";
	private static final String DELETE_CLIENT = "DELETE FROM CLIENT WHERE id=?;";
	private static final String GET_CLIENT = "SELECT * FROM CLIENT WHERE id=?;";
	
	private final static Logger LOGGER = LogManager.getLogger(ClientDAO.class);
	public ClientDAO() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void save(Client data) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
        try {
        	conn = cp.getConnection();
        	ps = conn.prepareStatement(SAVE_CLIENT, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,data.getName());
			ps.setString(2,data.getEmail());

			int rset = ps.executeUpdate();
			if(rset==1)
				LOGGER.info("Client saved");
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
	public void update(Client data) {
	}

	@Override
	public Client getById(long id) {
		Client client=null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
        try {
        	conn = cp.getConnection();
        	ps = conn.prepareStatement(GET_CLIENT);
			ps.setLong(1,id);

			rs = ps.executeQuery();
            if(rs.next())
            {
                client = new Client(rs.getString("name"), rs.getString("email"), rs.getLong("id"));
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
		return client;
	}

	@Override
	public void delete(Client data) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
        try {
        	conn = cp.getConnection();
        	ps = conn.prepareStatement(DELETE_CLIENT);
			ps.setLong(1,data.getId());

			int rset = ps.executeUpdate();
			if(rset!=0)
				LOGGER.info("Client deleted");
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
