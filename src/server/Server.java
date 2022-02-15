package server;

import main.MainFunction;
import entity.Account;
import entity.Constants;

public class Server {
	
	
	public int CreateAccount(String newAccountName, String newAccountPass, Constants.CURRENCY newAccountCurr) {
		MainFunction main = new MainFunction();
		//create new account with name, password, currency type, inital account balance
		Account a = new Account();
		a.setName(newAccountName);
		a.setPassword(newAccountPass);
		a.setCurrency(newAccountCurr);
		System.out.print("\n----------Account Created-----------\n" + "Account Number: " + a.getNumber() + "\n------------------------------------\n\n");
//		//return back to main menu
		main.printMenu(1);
		return a.getNumber();
	}
	
	public String closeAccount(String accountName, int accountNumber, String accountPass) {
		//close account with account number, name and password
		

		//server should check if the account got closed 
		MainFunction main = new MainFunction();
		//return back to main menu
		main.printMenu(1);
		return "account deleted";
	}
	
	public Double Deposit(String userAccountName, int userAccountNumber, String accountPass, Constants.CURRENCY currencyType, double depositSum) {
		//deposit into account with account number, name, password, currencytype, amount
		MainFunction main = new MainFunction();
		//return back to main menu
		main.printMenu(1);
		return 0.0;
	}
	
	public Double Withdraw(String userAccountName, int userAccountNumber, String accountPass, Constants.CURRENCY currencyType, double withdrawSum) {
		//deposit into account with account number, name, password, currencytype, withdraw
		MainFunction main = new MainFunction();
		//return back to main menu
		main.printMenu(1);
		return 0.0;
	}
	
	public Double TransferMoney(String userAccountName, int userAccountNumber, String accountPass, Constants.CURRENCY currencyType, int recipentAcc, double withdrawSum) {
		//deposit into account with account number, name, password, currencytype, withdraw
		MainFunction main = new MainFunction();
		//return back to main menu
		main.printMenu(1);
		return 0.0;
	}
}
