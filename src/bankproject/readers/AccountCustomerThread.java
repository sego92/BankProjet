package bankproject.readers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
//import java.util.Scanner;

import bankproject.entities.Account;
import bankproject.entities.Customer;
import bankproject.exceptions.SrvException;
import bankproject.services.SrvAccount;
import bankproject.services.SrvCustomer;

public class AccountCustomerThread extends Thread{
	
	private Customer splitDataCu (String line){
		Customer cu = new Customer();
		
		String[] result = line.split("\t\t\t");
		System.out.println(result[1]);
		System.out.println(result[2]);
		
		cu.setLastName(result[1]);
		cu.setFirstName(result[2]);
		
		
		return cu;
	}
	
	private Account splitDataAc (String line){
		Account ac = new Account();
		
		String[] result = line.split("\t\t\t");
		System.out.println(result[0]);
		System.out.println(result[1]);
		System.out.println(result[2]);
		
		ac.setPays(result[0]);
		
		ac.setSolde(Double.parseDouble(result[3]));
		
		ac.getCustomer().getId();
		
		
		return ac;
	}
	

	public void run (){
		//super.run();
		while(true){
			try {
				sleep(420);				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			File g = new File("c:/tmp/bank/input/account_customer.txt");
			if (g.exists()) {
//				Scanner scan = null;
//				try {
//					scan = new Scanner (g);
//				} catch (FileNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				while (scan.hasNextLine()) {
//					System.out.println(scan.nextLine());
//				}
//			scan.close();
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader(g));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String line = null;
			try {
				//lire ligne de titre colonnes
				reader.readLine();
						
				while ((line=reader.readLine()) !=null){
					System.out.println(line);
					Customer cu = splitDataCu(line);
					try {
						SrvCustomer.getINSTANCE().save(cu);
					} catch (SrvException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					Account ac = splitDataAc(line);
					
					String accountNumber = new String();
					ac.setAccountNumber(accountNumber);
					try {
						SrvAccount.getINSTANCE().save(ac);
					} catch (SrvException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			g.delete();
			} 
				
		}
	}

}
