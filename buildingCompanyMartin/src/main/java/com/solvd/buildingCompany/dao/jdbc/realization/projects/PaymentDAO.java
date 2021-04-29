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
import com.solvd.buildingCompany.dao.jdbc.realization.persons.ClientDAO;
import com.solvd.buildingCompany.dao.projects.IPaymentDAO;
import com.solvd.buildingCompany.model.machines.Company;
import com.solvd.buildingCompany.model.persons.Client;
import com.solvd.buildingCompany.model.projects.Contract;
import com.solvd.buildingCompany.model.projects.Payment;

public class PaymentDAO extends AbstractDAO implements IPaymentDAO{

	private static final String SAVE_PAYMENT = "INSERT INTO PAYMENTS(date, amount, serial_number, contract_id) values (?,?,?,?);";
	private static final String DELETE_PAYMENT= "DELETE FROM PAYMENTS WHERE id=?;";
	private static final String GET_PAYMENT = "SELECT * FROM PAYMENTS WHERE id=?;";
	private static final String GET_PAYMENTS_BY_CONTRACT= "SELECT * FROM PAYMENTS WHERE contract_id=?;";
	
	private final static Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
	
	public PaymentDAO() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void save(Payment data) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
        try {
        	conn = cp.getConnection();
        	ps = conn.prepareStatement(SAVE_PAYMENT, Statement.RETURN_GENERATED_KEYS);
        	ps.setDate(1,new Date(data.getDate().getTime()));
        	ps.setDouble(2, data.getAmount());
        	ps.setString(3, data.getSerialNumber());
        	ps.setLong(4, data.getContract_id());
			int rset = ps.executeUpdate();
			if(rset==1)
				LOGGER.info("Payment saved");
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
	public void update(Payment data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Payment getById(long id) {
		Payment payment=null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
        try {
        	conn = cp.getConnection();
        	ps = conn.prepareStatement(GET_PAYMENT);
			ps.setLong(1,id);

			rs = ps.executeQuery();
            if(rs.next())
            {
                payment = new Payment(rs.getLong("id"),new java.util.Date(rs.getDate("date").getTime()),rs.getDouble("amount"), rs.getString("serial_number"));
                payment.setContract_id(rs.getLong("contract_id"));
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
		return payment;
	}

	@Override
	public void delete(Payment data) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
        try {
        	conn = cp.getConnection();
        	ps = conn.prepareStatement(DELETE_PAYMENT);
			ps.setLong(1,data.getId());

			int rset = ps.executeUpdate();
			if(rset!=0)
				LOGGER.info("Payment deleted");
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
	public List<Payment> getPaymentsByContractId(long id) {
		List<Payment> payments=new ArrayList<Payment>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
        try {
        	conn = cp.getConnection();
        	ps = conn.prepareStatement(GET_PAYMENTS_BY_CONTRACT);
			ps.setLong(1,id);

			rs = ps.executeQuery();
            while(rs.next())
            {
            	Payment payment = new Payment(rs.getLong("id"),new java.util.Date(rs.getDate("date").getTime()),rs.getDouble("amount"), rs.getString("serial_number"));
                payment.setContract_id(rs.getLong("contract_id"));
                payments.add(payment);
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
		return payments;
	}

}
