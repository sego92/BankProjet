package bankproject;


import java.sql.SQLException;
import java.util.Scanner;

import bankproject.entities.Account;
import bankproject.entities.Customer;
import bankproject.entities.Operation;
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
		

		for (;;){
			
			Scanner sc = new Scanner (System.in);
			System.out.println("Veuillez saisir un lastName :");
			String sLastname = sc.nextLine();
			System.out.println("Veuillez saisir un firstName :");
			String sFirstName = sc.nextLine();
			try {
				Customer cu = SrvCustomer.getINSTANCE().get(sLastname,sFirstName);
				if (cu==null){
					System.out.println("cette persoone n'a pas de compte dans cette banque");
				}else {
					Account ac = (Account) SrvAccount.getINSTANCE().get(cu.getId());
					System.out.println("AccountNumber =" + ac.getAccountNumber());
					Operation op = (Operation) SrvOperation.getINSTANCE().get(ac.getId());
					System.out.println("Credit/Debit = " + op.getCreditDebit());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	}

}
