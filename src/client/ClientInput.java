package client;

import java.util.Currency;
import java.util.Set;

import entity.Account;
import entity.Message;
import entity.Constants;
import entity.Constants.CURRENCY;
import server.Server;

public class ClientInput
{
	private Server server = new Server();
	public CURRENCY[] allCurrency = CURRENCY.values();
	public boolean currencyCheck = false; 
	public CURRENCY selectedCurrency;
	public String userName = "";
	public int userAccount = 0;
	public String userPassword = "";
	public String userCurrencyType;
	private int portNumber;
	private int serverIPAddress;
	private Client client = new Client(serverIPAddress, portNumber);

	public ClientInput() {
	}

	public void getGeneralUserInput(int option) {
		System.out.println("Please Enter a Name: ");
		userName = GetUserInput.userInputString();
		// check if it is a unique name 
		//check the password meets the requirements 
		if(option == 1 || option == 2) {
			if(option == 2) {
				System.out.println("Please Enter Account number: ");
				userAccount = GetUserInput.userInputInt();
			}
			System.out.println("Please Enter Currency Type (e.g. USD, SGD, EUR): ");
			userCurrencyType = GetUserInput.userInputString();
			currencyCheckFunc(userCurrencyType);
			while(currencyCheck == false) {
				System.out.println("Please insert a valid currency type");
				userCurrencyType = GetUserInput.userInputString();
				currencyCheckFunc(userCurrencyType);
			} 	
		} else {
			System.out.println("Please Enter Account number: ");
			userAccount = GetUserInput.userInputInt();
		}
		System.out.println("Please Enter Password: ");
		userPassword = GetUserInput.userInputString();
	}

	public void openNewAccount() {
		getGeneralUserInput(1);
		System.out.printf("Name: " + userName + " Password: " + userPassword + " Currency Type: " + userCurrencyType);
		client.createAccount(userName, userPassword, selectedCurrency);
	}

	public void currencyCheckFunc(String newAccountCurr) {
		currencyCheck = false;
		for (CURRENCY currency : allCurrency)
		{
			if (currency.toString().equals(newAccountCurr.toUpperCase()))
			{
				currencyCheck = true;
				selectedCurrency = currency;
			}
		}
	}

	public void closeExistingAccount() {
		getGeneralUserInput(0);
		System.out.printf("Name: " + userName + " Account Number: " + userAccount + " Password: " + userPassword + "\n");
		client.closeAccount(userName, userAccount, userPassword);
	}

	public void depositOrWithdraw() {
		System.out.println("------Deposit or Withdraw------\n");
		getGeneralUserInput(2);
		System.out.println("\n---To deposit money, enter 1----\n---To withdraw money, enter 2---\n");
		int option = GetUserInput.userInputInt();
		switch(option) {
		case 1: 
			System.out.println("--------Deposit Money--------\n");
			System.out.println("Please Enter the sum to deposit.");
			double depositSum = GetUserInput.userInputDouble();
			System.out.printf("Name: " + userName + " Account Number: " + userAccount + " Password: " + userPassword + " Currency Type: " + selectedCurrency + " Sum: " + depositSum + "\n");
			client.depositMoney(userName, userAccount, userPassword, selectedCurrency, depositSum);
			break;
		case 2: 
			System.out.println("--------Withdraw Money--------\n");
			System.out.println("Please Enter the sum to withdraw.");
			double withdrawSum = GetUserInput.userInputDouble();
			System.out.printf("Name: " + userName + " Account Number: " + userAccount + " Password: " + userPassword + " Currency Type: " + selectedCurrency + " Sum: " + withdrawSum + "\n");
			client.withdrawMoney(userName, userAccount, userPassword, selectedCurrency, withdrawSum);
			break;
		}
	}

	//monitor updates for a time period
	public void monitorUpdates() {
		System.out.println("--------Monitor Updates--------\n");
		System.out.println("Please enter the monitor interval time: ");
		int monitorIntervalTime = GetUserInput.userInputInt();
		System.out.println("Monitor Interval Time: " + monitorIntervalTime);
		client.monitorUpdates(monitorIntervalTime);

		//get the system time in nano seconds
		long monitorStartTime = System.nanoTime();
		//while the updates are within the period of time
		while(((System.nanoTime() - monitorStartTime)/ 1e9) <= monitorIntervalTime){
			//get the updates from server
		}
		System.out.println("Monitoring Period ended...");
	}

	//transfer balance to another account 
	//function 1 
	public void transferMoney() {
		System.out.println("--------Transfer Money--------\n");
		getGeneralUserInput(2);
		System.out.println("Please Enter the Account of Recipent: ");
		int recipentAcc =  GetUserInput.userInputInt();
		System.out.println("Please Enter amount to tranfer: ");
		double transAmount = GetUserInput.userInputDouble();
		System.out.printf("Name: " + userName + " Account Number: " + userAccount + " Password: " + userPassword + " Currency Type: " + selectedCurrency + " Recipent Account: " + recipentAcc + " Sum: " + transAmount + "\n");
		client.transferMoney(userName, userAccount, userPassword, selectedCurrency, recipentAcc, transAmount);
	}

	//function 2 
	//check balance (Name, Password, Account Number) 
	public void checkBalance() {
		System.out.println("--------Check Balance--------\n");
		getGeneralUserInput(0);
		System.out.printf("Name: " + userName + " Account Number: " + userAccount + " Password: " + userPassword + "\n");
		client.checkBalanceOfAccount(userName, userAccount, userPassword);

	}
}
