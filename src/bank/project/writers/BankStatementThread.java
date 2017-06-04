package bank.project.writers;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class BankStatementThread extends Thread {
	
	
	public void run (){
		//super.run();
		while(true){
			try {
				sleep(3500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("BankStatementThread");
			File h = new File("c:/tmp/bank/output/country.txt");
			File b = new File("c:/tmp/bank/output/debtors.txt");
			File u = new File("c:/tmp/bank/output/creditors.txt");
				
		}
		
	}

}
