package bankproject.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bankproject.entities.Account;
import bankproject.entities.BankEntity;
import bankproject.exceptions.SrvException;
import bankproject.entities.Operation;

public class SrcOperation extends BankService {
	private static SrcOperation INSTANCE = new SrcOperation();
	
	public static SrcOperation getINSTANCE() {
		return INSTANCE;
	}


	public static void setINSTANCE(SrcOperation iNSTANCE) {
		INSTANCE = iNSTANCE;
	}
	
	
	public String createTableOperation () throws SQLException {
		StringBuilder sql =  new StringBuilder();
		sql.append("CREATE TABLE IF NOT EXISTS operation (");
		sql.append("id INTEGER PRIMARY KEY AUTOINCREMENT,");
		sql.append("AccountNumber VARCHAR(255),");
		sql.append("Operation DOUBLE,");
		sql.append("DateOperation DATETIME");
		sql.append(")");
		
		Statement st = SQLiteManager.getConnection().createStatement();	
		st.execute(sql.toString());
		return sql.toString();
	}
	
	public void createOperation (Operation entity) throws SQLException {
    	// TODO 
    	PreparedStatement ps = null;
		Connection connection = null;
		java.sql.Date dateSQL = new java.sql.Date(entity.getDateOperation().getTime());
		String sql = "INSERT INTO operation (accountNumber, creditDebit, dateOperation) VALUES (?, ?, ?)";
		try {
			connection = SQLiteManager.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, entity.getAccountNumber());
			ps.setDouble(3, entity.getCreditDebit());
			ps.setDate(4, dateSQL);
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
    
    
    public void updateOperation (Operation entity) throws SQLException {
    	// TODO 
    	PreparedStatement ps = null;
		Connection connection = null;
		java.sql.Date dateSQL = new java.sql.Date(entity.getDateOperation().getTime());
		String sql = "UPDATE operation SET accountNumber=?, creditDebit=?, dateOperation=?, WHERE ID=?";
		try {
			connection = SQLiteManager.getConnection();
			ps = connection.prepareStatement(sql.toString());
			//ps.setString(1, entity.createAccountNumber());
			ps.setString(2, entity.getAccountNumber());
			ps.setDouble(3, entity.getCreditDebit());
			ps.setDate(4, dateSQL);
			ps.setInt(5, entity.getId());
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
    
    
    protected Operation readEntity (ResultSet rs) throws SQLException {
    	Operation operation = new Operation();
    	operation.setId(rs.getInt("id"));
    	operation.setAccountNumber(rs.getString("accountNumber"));
    	operation.setCreditDebit(rs.getDouble("creditDebit"));
    	java.util.Date dateUtil = new java.util.Date(rs.getDate("dateOperation").getTime());
		operation.setDateOperation(dateUtil);
	
		return operation;
    }


	@Override
	public void save(BankEntity entity) throws SrvException, SQLException {
		if (entity instanceof Operation) {
			Operation operation = (Operation)entity;
			if (operation.getId() == null) {
				createOperation(operation);
			} else {
				updateOperation(operation);
			}
		} else {
			throw new SrvException("Utilisation du mauvais service");
		}
	
		
	}
	
    
	public void deleteOperation (String accountNumber, Double creditDebit, Date dateOperation, int id) throws SQLException {
    	// TODO 
    	PreparedStatement ps = null;
		Connection connection = null;
		try {
			connection = SQLiteManager.getConnection();

			StringBuilder sql =  new StringBuilder();
			
			sql.append("DELETE account WHERE id = ?");
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, accountNumber);
			ps.setDouble(2, creditDebit);
			ps.setDate(3, dateOperation);
			ps.setInt(4, id);
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

