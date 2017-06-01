package bankproject.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bankproject.entities.Account;
import bankproject.entities.BankEntity;
import bankproject.entities.Customer;
import bankproject.exceptions.SrvException;

public class SrvAccount extends BankService {
	private static SrvAccount INSTANCE = new SrvAccount();
	
	public static SrvAccount getINSTANCE() {
		return INSTANCE;
	}


	public static void setINSTANCE(SrvAccount iNSTANCE) {
		INSTANCE = iNSTANCE;
	}
	
	
	public String createTableAccount() throws SQLException {
		StringBuilder sql =  new StringBuilder();
		sql.append("CREATE TABLE IF NOT EXISTS account (");
		sql.append("id INTEGER PRIMARY KEY AUTOINCREMENT,");
		sql.append("AccountNumber VARCHAR(255),");
		sql.append("LastName VARCHAR(255),");
		sql.append("FirstName VARCHAR(255),");
		sql.append("Solde DOUBLE");
		sql.append(")");
		
		Statement st = SQLiteManager.getConnection().createStatement();
		st.execute(sql.toString());
		return sql.toString();
	}

	
	public void createAccount (Account entity) throws SQLException {
    	// TODO 
    	PreparedStatement ps = null;
		Connection connection = null;
		String sql = "INSERT INTO account (accountNumber, lastName, firstName, solde) VALUES (?, ?, ?, ?)";
		try {
			connection = SQLiteManager.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, entity.getAccountNumber());
			ps.setString(2, entity.getLastName());
			ps.setString(3, entity.getFirstName());
			ps.setDouble(4, entity.getSolde());
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
    
    
    public void updateAccount (Account entity) throws SQLException {
    	// TODO 
    	PreparedStatement ps = null;
		Connection connection = null;
		String sql = "UPDATE account SET accountNumber=?, lastName=?, firstName=?, solde=? WHERE ID=?";
		try {
			connection = SQLiteManager.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, entity.getAccountNumber());
			ps.setString(2, entity.getLastName());
			ps.setString(3, entity.getFirstName());
			ps.setDouble(4, entity.getSolde());
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
    
    
    protected Account readEntity (ResultSet rs) throws SQLException {
    	Account account = new Account();
		account.setId(rs.getInt("id"));
		account.setFirstName(rs.getString("firstname"));
		account.setLastName(rs.getString("lastname"));
		account.setAccountNumber(rs.getString("accountNumber"));
		account.setSolde(rs.getDouble("solde"));
	
		return account;
    }


	@Override
	public void save(BankEntity entity) throws SrvException, SQLException {
		if (entity instanceof Account) {
			Account account = (Account)entity;
			if (account.getId() == null) {
				createAccount(account);
			} else {
				updateAccount(account);
			}
		} else {
			throw new SrvException("Utilisation du mauvais service");
		}
	
		
	}
	
    
	public void deleteAccount (String accountNumber, String lastName, String firstName, Double solde, int id) throws SQLException {
    	// TODO 
    	PreparedStatement ps = null;
		Connection connection = null;
		try {
			connection = SQLiteManager.getConnection();

			StringBuilder sql =  new StringBuilder();
			
			sql.append("DELETE account WHERE id = ?");
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, accountNumber);
			ps.setString(2, lastName);
			ps.setString(3, firstName);
			ps.setDouble(4, solde);
			ps.setInt(5, id);
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