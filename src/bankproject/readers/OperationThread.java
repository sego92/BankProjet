package bankproject.readers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class OperationThread extends Thread {
	
	public OperationThread () {
		
	}
	
	public void run (){
		//super.run();
		while(true){
			try {
				sleep(3000);				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("OperationThread");
			File f = new File("c:/tmp/bank/input/operation.txt");
			if (f.exists()) {
				System.out.println("OperationThread exist");
				Scanner scan = null;
				try {
					scan = new Scanner (f);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				while (scan.hasNextLine()) {
					System.out.println(scan.nextLine());
				}
			scan.close();
			boolean i = f.delete();
				
			if(i)System.out.println("ok o");
			else System.out.println("pas ok o");
			} 
			
		}
	}

}
