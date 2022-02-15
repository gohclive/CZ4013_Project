package client;

import java.util.Currency;
import java.util.Set;

import entity.Account;
import entity.Constants;
import entity.Constants.CURRENCY;
import server.Server;

public class ClientInput
{
	public Server server = new Server();
	public CURRENCY[] allCurrency = CURRENCY.values();
	public boolean currencyCheck = false; 
	public CURRENCY selectedCurrency;
	public String userName = "";
	public int userAccount = 0;
	public String userPassword = "";
	public String userCurrencyType;

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
		server.CreateAccount(userName, userPassword, selectedCurrency);
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
		server.closeAccount(userName, userAccount, userPassword);
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
			server.Deposit(userName, userAccount, userPassword, selectedCurrency, depositSum);
			break;
		case 2: 
			System.out.println("--------Withdraw Money--------\n");
			System.out.println("Please Enter the sum to withdraw.");
			double withdrawSum = GetUserInput.userInputDouble();
			System.out.printf("Name: " + userName + " Account Number: " + userAccount + " Password: " + userPassword + " Currency Type: " + selectedCurrency + " Sum: " + withdrawSum + "\n");
			server.Withdraw(userName, userAccount, userPassword, selectedCurrency, withdrawSum);
			break;
		}
	}

	public void monitorUpdates() {
		
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
		server.TransferMoney(userName, userAccount, userPassword, selectedCurrency, recipentAcc, transAmount);
	}
	
	//
	//function 2 
}
