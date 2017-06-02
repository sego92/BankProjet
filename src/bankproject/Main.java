package bankproject;

import bankproject.readers.*;

public class Main {

	public static void main(String[] args) {
		OperationThread xxx = new OperationThread ();
		AccountCustomerThread yyy = new AccountCustomerThread ();
		yyy.start();
		xxx.start();
		

	}

}
