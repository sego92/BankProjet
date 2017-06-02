package bankproject;


import java.sql.SQLException;

import bankproject.readers.*;
import bankproject.services.SQLiteManager;
import bankproject.services.SrvAccount;
import bankproject.services.SrvCustomer;
import bankproject.services.SrvOperation;

public class Main {

	public static void main(String[] args) throws SQLException {
//		OperationThread xxx = new OperationThread ();
//		AccountCustomerThread yyy = new AccountCustomerThread ();
//		yyy.start();
//		xxx.start();
		
		SrvCustomer srvCustomer = SrvCustomer.getINSTANCE();
		SrvAccount srvAccount = SrvAccount.getINSTANCE();
		SrvOperation srvOperation = SrvOperation.getINSTANCE();
		SQLiteManager.getConnection();
		srvCustomer.createTableCustomer();
		srvAccount.createTableAccount();
		srvOperation.createTableOperation();
		

	}

}
