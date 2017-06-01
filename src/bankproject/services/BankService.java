package bankproject.services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import bankproject.services.SQLiteManager;
import java.sql.ResultSet;

import bankproject.entities.BankEntity;
import bankproject.exceptions.SrvException;

public abstract class BankService {
	private String bankTable;
	
	
	public String getBankTable() {
		return bankTable;
	}



	public void setBankTable(String bankTable) {
		this.bankTable = bankTable;
	}
	
	public BankEntity get(Integer id) throws Exception {
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		BankEntity result = null;
		
		StringBuilder query = new StringBuilder("SELECT * FROM ");
		query.append(getBankTable());
		query.append(" WHERE id = ?");
		
		try {
			connection = SQLiteManager.getConnection();
			pst = connection.prepareStatement(query.toString());
			pst.setInt(1, id);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				result = readEntity(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}
			
			if (pst != null) {
				pst.close();
			}
		}
		
		return result;
	}
	
	
	protected abstract BankEntity readEntity(ResultSet rs) throws SQLException, Exception;



	public abstract void save(BankEntity entity) throws SrvException, SQLException;
		
}
