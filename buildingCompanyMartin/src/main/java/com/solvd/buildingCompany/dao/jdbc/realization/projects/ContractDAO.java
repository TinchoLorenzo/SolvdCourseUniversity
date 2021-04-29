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
import com.solvd.buildingCompany.dao.jdbc.realization.location.CountryDAO;
import com.solvd.buildingCompany.dao.jdbc.realization.persons.ClientDAO;
import com.solvd.buildingCompany.dao.projects.IContractDAO;
import com.solvd.buildingCompany.model.location.City;
import com.solvd.buildingCompany.model.location.Country;
import com.solvd.buildingCompany.model.machines.Company;
import com.solvd.buildingCompany.model.persons.Client;
import com.solvd.buildingCompany.model.projects.Contract;
import com.solvd.buildingCompany.model.projects.Payment;
import com.solvd.buildingCompany.model.projects.Project;

public class ContractDAO extends AbstractDAO implements IContractDAO {

	private static final String SAVE_CONTRACT= "INSERT INTO CONTRACTS (begin_date, end_date, prize, project_id, client_id) values (?,?,?,?,?);";
	private static final String DELETE_CONTRACT= "DELETE FROM CONTRACTS WHERE id=?;";
	private static final String GET_CONTRACT = "SELECT * FROM CONTRACTS WHERE id=?;";
	private static final String GET_CONTRACTS_BY_CLIENT = "SELECT * FROM CONTRACTS WHERE client_id=?;";
	private static final String GET_CONTRACTS_BY_PROJECT = "SELECT * FROM CONTRACTS  WHERE project_id=?;";

	private final static Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

	public ContractDAO() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void save(Contract data) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
        try {
        	conn = cp.getConnection();
        	ps = conn.prepareStatement(SAVE_CONTRACT, Statement.RETURN_GENERATED_KEYS);
        	ps.setDate(1,new Date(data.getBegin_date().getTime()));
        	ps.setDate(2,new Date(data.getEnd_date().getTime()));
        	ps.setDouble(3,data.getPrize());
        	ps.setDouble(4,data.getProject().getId());
        	ps.setDouble(5,data.getClient().getId());

			int rset = ps.executeUpdate();
			if(rset==1)
				LOGGER.info("Contract saved");
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
	public void update(Contract data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Contract getById(long id) {
		Contract contract=null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
        try {
        	conn = cp.getConnection();
        	ps = conn.prepareStatement(GET_CONTRACT);
			ps.setLong(1,id);

			rs = ps.executeQuery();
            if(rs.next())
            {
                contract = new Contract(rs.getLong("id"), new java.util.Date(rs.getDate("begin_date").getTime()), new java.util.Date(rs.getDate("end_date").getTime()),
                		rs.getDouble("prize"),null, null, null);
                Client cd = new Client();
                cd.setId(rs.getLong("client_id"));
                contract.setClient(cd);
                Project p = new Project();
                p.setId(rs.getLong("project_id"));
                contract.setProject(p);
                PaymentDAO payd = new PaymentDAO();
                contract.setPayments(payd.getPaymentsByContractId(contract.getId()));
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
		return contract;
	}

	@Override
	public void delete(Contract data) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
        try {
        	conn = cp.getConnection();
        	ps = conn.prepareStatement(DELETE_CONTRACT);
			ps.setLong(1,data.getId());

			int rset = ps.executeUpdate();
			if(rset!=0)
				LOGGER.info("Contract deleted");
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
	public List<Contract> getContractsByClientId(long id) {
		List<Contract> contracts=new ArrayList<Contract>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
        ClientDAO clientDAO = new ClientDAO();
		Client client= clientDAO.getById(id);
        try {
        	conn = cp.getConnection();
        	ps = conn.prepareStatement(GET_CONTRACTS_BY_CLIENT);
			ps.setLong(1,id);

			rs = ps.executeQuery();
            while(rs.next())
            {
            	Contract contract = new Contract(rs.getLong("id"), new java.util.Date(rs.getDate("begin_date").getTime()), new java.util.Date(rs.getDate("end_date").getTime()),
                		rs.getDouble("prize"),null, null, null);               
            	contract.setClient(client);
            	ProjectDAO pd = new ProjectDAO();
                contract.setProject(pd.getById(rs.getLong("project_id")));
                PaymentDAO payd = new PaymentDAO();
                contract.setPayments(payd.getPaymentsByContractId(contract.getId()));
                contracts.add(contract);
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
		return contracts;
	}

	@Override
	public List<Contract> getContractsByProjectId(long id) {
		List<Contract> contracts=new ArrayList<Contract>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
        ProjectDAO projectDAO = new ProjectDAO();
        Project project= projectDAO.getById(id);
        try {
        	conn = cp.getConnection();
        	ps = conn.prepareStatement(GET_CONTRACTS_BY_PROJECT);
			ps.setLong(1,id);

			rs = ps.executeQuery();
            while(rs.next())
            {
            	Contract contract = new Contract(rs.getLong("id"), new java.util.Date(rs.getDate("begin_date").getTime()), new java.util.Date(rs.getDate("end_date").getTime()),
                		rs.getDouble("prize"),null, null, null);               

            	ClientDAO cd = new ClientDAO();
                contract.setClient(cd.getById(rs.getLong("client_id")));
                contract.setProject(project);
                PaymentDAO payd = new PaymentDAO();
                contract.setPayments(payd.getPaymentsByContractId(contract.getId()));
                contracts.add(contract);
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
		return contracts;
	}

}
