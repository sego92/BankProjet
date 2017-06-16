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

public class SrvOperation extends BankService {
	private static SrvOperation INSTANCE = new SrvOperation();
	
	public static SrvOperation getINSTANCE() {
		return INSTANCE;
	}


	public static void setINSTANCE(SrvOperation iNSTANCE) {
		INSTANCE = iNSTANCE;
	}
	
	
	public String createTableOperation () throws SQLException {
		StringBuilder sql =  new StringBuilder();
		sql.append("CREATE TABLE IF NOT EXISTS operation (");
		sql.append("id INTEGER PRIMARY KEY AUTOINCREMENT,");
		sql.append("DateOperation DATETIME,");
		sql.append("CreditDebit DOUBLE,");
		sql.append("accountID INTEGER,");
		sql.append("FOREIGN KEY (accountID) REFERENCES account (id) ON DELETE CASCADE");
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
		String sql = "INSERT INTO operation (dateOperation, creditDebit, accountID) VALUES (?, ?, ?)";
		try {
			connection = SQLiteManager.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setDate(1, dateSQL);
			ps.setDouble(2, entity.getCreditDebit());
			ps.setInt(3, entity.getAccount().getId());
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
		String sql = "UPDATE operation SET accountID=?, creditDebit=?, dateOperation=?, WHERE ID=?";
		try {
			connection = SQLiteManager.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setInt(1, entity.getAccount().getId());
			ps.setDouble(2, entity.getCreditDebit());
			ps.setDate(3, dateSQL);
			ps.setInt(4, entity.getId());
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
    

    protected Operation readEntity (ResultSet rs) throws Exception {
    	Operation operation = new Operation();
    	
    	Integer accountID = rs.getInt("accountID");
    	
    	SrvAccount srvAccount = SrvAccount.getINSTANCE();
    	Account account = (Account) srvAccount.get(accountID);
    	
    	operation.setId(rs.getInt("id"));
    	operation.setAccount(account);
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
			throw new SrvException();
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

