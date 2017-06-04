package bank.project.writers;


import java.io.FileWriter;
import java.io.IOException;

public class BankStatementThread extends Thread {
	
	
	public void run (){
		//super.run();
		while(true){
			try {
				sleep(780000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("BankStatementThread");
			try {
				FileWriter country = new FileWriter ("c:/tmp/bank/output/country.txt");
				country.write("");
				country.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				FileWriter debtors = new FileWriter ("c:/tmp/bank/output/debtors.txt");
				debtors.write("");
				debtors.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				FileWriter creditors = new FileWriter ("c:/tmp/bank/output/creditors.txt");
				creditors.write("");
				creditors.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		}
		
	}

}
