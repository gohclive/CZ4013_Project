package server;

import main.MainFunction;
import entity.Account;
import entity.Constants;

public class Server {
	
	
	public int CreateAccount(String newAccountName, String newAccountPass, Constants.CURRENCY newAccountCurr) {
		MainFunction main = new MainFunction();
		//create new account with name, password, currency type, inital account balance
		Account a = new Account();

		return a.getNumber();
	}
	
	public String closeAccount(String accountName, int accountNumber, String accountPass) {
		//close account with account number, name and password
	
		return "account deleted";
	}
	
	public Double Deposit(String userAccountName, int userAccountNumber, String accountPass, Constants.CURRENCY currencyType, double depositSum) {
		//deposit into account with account number, name, password, currencytype, amount
		return 0.0;
	}
	
	public Double Withdraw(String userAccountName, int userAccountNumber, String accountPass, Constants.CURRENCY currencyType, double withdrawSum) {
		//deposit into account with account number, name, password, currencytype, withdraw
		return 0.0;
	}
	
	public Double TransferMoney(String userAccountName, int userAccountNumber, String accountPass, Constants.CURRENCY currencyType, int recipentAcc, double withdrawSum) {
		//deposit into account with account number, name, password, currencytype, withdraw
		return 0.0;
	}
	
	public String checkBalanceOfAccount(String userName, int userAccountNumber, String userPassword) {	
		
		return "Account Balance";
	}
	
	public void monitorUpdates(int intervals) {
		
	}
}
