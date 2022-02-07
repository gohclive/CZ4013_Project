package server;

import entity.Account;

public class Server {
	
	public int CreateAccount() {
		//create new account with name, password, currency type, inital account balance
		Account a = new Account();
		
		return a.getNumber();
	}
	
	public String closeAccount() {
		//close account with account number, name and password
		return "account deleted";
	}
	
	public Double Deposit() {
		//deposit into account with account number, name, password, currencytype, amount
		return 0.0;
	}
	
	public Double Withdraw() {
		//deposit into account with account number, name, password, currencytype, withdraw
		return 0.0;
	}

}
