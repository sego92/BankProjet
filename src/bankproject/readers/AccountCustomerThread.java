package bankproject.readers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AccountCustomerThread extends Thread{
	public AccountCustomerThread () {
			
		}
		
		public void run (){
			//super.run();
			while(true){
				try {
					sleep(2500);				
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("AccountCustomerThread");
				File g = new File("c:/tmp/bank/input/account_customer.txt");
				if (g.exists()) {
					System.out.println("AccountCustomerThread exist");
					Scanner scan = null;
					try {
						scan = new Scanner (g);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					while (scan.hasNextLine()) {
						System.out.println(scan.nextLine());
					}
				scan.close();
				boolean i = g.delete();
					
				if(i)System.out.println("ok ac");
				else System.out.println("pas ok ac");
				} 
				
			}
		}

}
