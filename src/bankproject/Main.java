package bankproject;

import java.sql.Connection;
import java.sql.Statement;

import bankproject.services.SQLiteManager;
import bankproject.services.SrvCustomer;

public class Main {

	public static void main(String[] args) {
		SrvCustomer srvCustomer = SrvCustomer.getINSTANCE();
		Connection connection = SQLiteManager.getConnection ();
		Statement st = connection.createStatement();
		st.execute(srvCustomer.createTableCustomer());

	}

}
