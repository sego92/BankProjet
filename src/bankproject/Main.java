package bankproject;


import java.sql.SQLException;

import bankproject.readers.*;
import bankproject.services.SQLiteManager;
import bankproject.services.SrvAccount;
import bankproject.services.SrvCustomer;
import bankproject.services.SrvOperation;

public class Main {

	public static void main(String[] args) throws SQLException {
		
		SrvCustomer srvCustomer = SrvCustomer.getINSTANCE();
		srvCustomer.setBankTable("customer");
		SrvAccount srvAccount = SrvAccount.getINSTANCE();
		srvAccount.setBankTable("account");
		SrvOperation srvOperation = SrvOperation.getINSTANCE();
		srvOperation.setBankTable("operation");
		SQLiteManager.getConnection();
		srvCustomer.createTableCustomer();
		srvAccount.createTableAccount();
		srvOperation.createTableOperation();
		
		OperationThread xxx = new OperationThread ();
		AccountCustomerThread yyy = new AccountCustomerThread ();
		yyy.start();
		xxx.start();
		
		

	}

}
