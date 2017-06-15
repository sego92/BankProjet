package bankproject.readers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
//import java.util.Scanner;

import bankproject.entities.Account;
import bankproject.entities.Operation;
import bankproject.exceptions.SrvException;
import bankproject.services.SrvOperation;

public class OperationThread extends Thread {
		
	private Operation splitData (String line){
		Operation op = new Operation();
		
		String[] result = line.split("\t\t\t");
		System.out.println(result[0]);
		System.out.println(result[1]);
		System.out.println(result[2]);
		
		op.setCreditDebit(Double.parseDouble(result[0]));
		op.getAccount().getId();
		
		
		return op;
	}
	
	private Account splitDataAc (String line){
		Account ac = new Account();
		
		String[] result = line.split("\t\t\t");
		
		ac.setAccountNumber(result[1]);
		return ac;
	}
	
	public void run (){
		//super.run();
		while(true){
			try {
				sleep(660);				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			File f = new File("c:/tmp/bank/input/operation.txt");
			if (f.exists()) {
				Date dateOperation = new Date();
//				Scanner scan = null;
//				try {
//					scan = new Scanner (f);
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
					reader = new BufferedReader(new FileReader(f));
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
						Account ac = splitData(line);
						Operation op = splitData(line);
						op.setDateOperation(dateOperation);
					    try {
							SrvOperation.getINSTANCE().save(op);
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
			f.delete();
			} 
			
		}
	}

}
