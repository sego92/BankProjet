package bankproject.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import bankproject.services.SQLiteManager;
import bankproject.entities.BankEntity;
import bankproject.entities.Customer;
import bankproject.exceptions.SrvException;

public class SrvCustomer extends BankService {
	private static SrvCustomer INSTANCE = new SrvCustomer();
	
	public static SrvCustomer getINSTANCE() {
		return INSTANCE;
	}


	public static void setINSTANCE(SrvCustomer iNSTANCE) {
		INSTANCE = iNSTANCE;
	}
	
	
	public String createTableCustomer() throws SQLException {
		StringBuilder sql =  new StringBuilder();
		sql.append("CREATE TABLE IF NOT EXISTS customer (");
		sql.append("id INTEGER PRIMARY KEY AUTOINCREMENT,");
		sql.append("LastName VARCHAR(255),");
		sql.append("FirstName VARCHAR(255)");
		sql.append(")");
		
		Statement st = SQLiteManager.getConnection().createStatement();
		st.execute(sql.toString());
		return sql.toString();
	}
	
	
	public void createCustomer (Customer entity) throws SQLException {
    	// TODO 
    	PreparedStatement ps = null;
		Connection connection = null;
		String sql = "INSERT INTO customer (lastName, firstName) VALUES (?, ?)";
		try {
			connection = SQLiteManager.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, entity.getLastName());
			ps.setString(2, entity.getFirstName());
			ps.execute();
		}catch (SQLException e) {
			
		} finally {
			if (ps != null) {
				ps.close();
			}
			
			if (connection != null) {
				connection.close();
			}
		}
    }
    
    
    public void updateCustomer (Customer entity) throws SQLException {
    	// TODO 
    	PreparedStatement ps = null;
		Connection connection = null;
		String sql = "UPDATE customer SET lastName=?, firstName=? WHERE ID=?";
		try {
			connection = SQLiteManager.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, entity.getLastName());
			ps.setString(2, entity.getFirstName());
			ps.setInt(3, entity.getId());
			ps.execute();
		}catch (SQLException e) {
			
		} finally {
			if (ps != null) {
				ps.close();
			}
			
			if (connection != null) {
				connection.close();
			}
		}
    }
    
    
    protected Customer readEntity (ResultSet rs) throws SQLException {
    	Customer customer = new Customer();
		customer.setId(rs.getInt("id"));
		customer.setFirstName(rs.getString("firstname"));
		customer.setLastName(rs.getString("lastname"));
	
		return customer;
    }


	@Override
	public void save(BankEntity entity) throws SrvException, SQLException {
		if (entity instanceof Customer) {
			Customer customer = (Customer)entity;
			if (customer.getId() == null) {
				createCustomer(customer);
			} else {
				updateCustomer(customer);
			}
		} else {
			throw new SrvException();
		}
	
		
	}
	
    
	public void deleteCustomer (String lastName, String firstName, int id) throws SQLException {
    	// TODO 
    	PreparedStatement ps = null;
		Connection connection = null;
		try {
			connection = SQLiteManager.getConnection();

			StringBuilder sql =  new StringBuilder();
			
			sql.append("DELETE customer WHERE id = ?");
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, lastName);
			ps.setString(2, firstName);
			ps.setInt(3, id);
			ps.execute();
		}catch (SQLException e) {
			
		} finally {
			if (ps != null) {
				ps.close();
			}
			
			if (connection != null) {
				connection.close();
			}
		}
    }
}


