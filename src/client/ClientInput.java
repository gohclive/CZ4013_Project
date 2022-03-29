package client;

import java.net.InetAddress;
import java.util.Currency;
import java.util.Set;

import entity.Account;
import entity.Message;
import entity.Constants;
import entity.Constants.CURRENCY;
import server.Server;

public class ClientInput
{
	public CURRENCY[] allCurrency = CURRENCY.values();
	public boolean currencyCheck = false;
	public boolean accountCheck = false;
	public boolean passwordCheck = false;
	public boolean accountNameCheck = false;
	public boolean moneyCheck = false;
	public CURRENCY selectedCurrency;
	public String userName = "";
	public int userAccount = 0;
	public double moneySum = 0;
	public String userPassword = "";
	public String userCurrencyType;
	private Client client = null;

	public ClientInput(Client c) {
		client = c;
	}

	public void getGeneralUserInput(int option) {
		System.out.println("Please Enter a Name: ");
		userName = GetUserInput.userInputString();
		accountNameCheck(userName);
		// check if it is a unique name
		// check the password meets the requirements
		if (option == 1 || option == 2) {
			if (option == 2) {
				System.out.println("Please Enter Account number: ");
				userAccount = GetUserInput.userInputInt();
				accountNumberCheck(userAccount);
			}
			System.out.println("Please Enter Currency Type (e.g. USD, SGD, EURO): ");
			userCurrencyType = GetUserInput.userInputString();
			currencyCheckFunc(userCurrencyType);
			while (currencyCheck == false) {
				System.out.println("Please insert a valid currency type");
				userCurrencyType = GetUserInput.userInputString();
				currencyCheckFunc(userCurrencyType);
			}
		} else {
			System.out.println("Please Enter Account number: ");
			userAccount = GetUserInput.userInputInt();
			accountNumberCheck(userAccount);

		}
		System.out.println("Please Enter Password: ");
		userPassword = GetUserInput.userInputString();
		accountPasswordCheck(userPassword);
	}

	public Message openNewAccount() {
		getGeneralUserInput(1);
		System.out.printf("Name: " + userName + " Password: " + userPassword + " Currency Type: " + userCurrencyType);
		Message msg = client.createAccount(userName, userPassword, selectedCurrency);
		return msg;
	}

	public void currencyCheckFunc(String newAccountCurr) {
		currencyCheck = false;
		for (CURRENCY currency : allCurrency) {
			if (currency.toString().equals(newAccountCurr.toUpperCase())) {
				currencyCheck = true;
				selectedCurrency = currency;
			}
		}
	}

	// account name (alphabets)
	public void accountNameCheck(String newAccountName) {
		accountNameCheck = false;
		while (accountNameCheck == false) {
			String regEx = "/^[A-Za-z]+$/";
			if ((userName != null) && (!userName.equals("")) && (userName.matches("^[a-zA-Z]*$"))) {
				accountNameCheck = true;
			} else {
				System.out.println("Please Enter a valid account name (alphabets only): ");
				userName = GetUserInput.userInputString();

			}
		}
	}

	// 9 digit number int
	public void accountNumberCheck(int newAccountNumber) {
		accountCheck = false;
		while (accountCheck == false) {
			if (String.valueOf(userAccount).length() <= 9) {
				System.out.println("Please insert a valid account number (<10 digits): ");
				userAccount = GetUserInput.userInputInt();
				// how to check for existing account number?
			} else {
				accountCheck = true;
			}
		}
	}

	// 8 alphanumeric length string
	public void accountPasswordCheck(String newPassword) {
		passwordCheck = false;
		while (passwordCheck == false) {
			if (userPassword.length() == 8 && userPassword.matches("^[a-zA-Z0-9]*$") && (!userPassword.equals(""))) {
				passwordCheck = true;
			} else {
				System.out.println("Please Enter a valid Password (8 alphanumeric password): ");
				userPassword = GetUserInput.userInputString();
			}
		}
	}

	public void moneyCheck(double values) {
		moneyCheck = false;
		while (moneyCheck == false) {
			System.out.println(moneySum);
			if (moneySum > 0) {
				moneyCheck = true;
			} else {
				System.out.println("Please Enter a valid sum.");
				moneySum = GetUserInput.userInputDouble();
			}
		}
	}

	public Message closeExistingAccount() {
		getGeneralUserInput(0);
		System.out
				.printf("Name: " + userName + " Account Number: " + userAccount + " Password: " + userPassword + "\n");
		Message msg = client.closeAccount(userName, userAccount, userPassword);
		return msg;
	}

	public Message depositOrWithdraw() {
		Message msg = null;
		System.out.println("------Deposit or Withdraw------\n");
		getGeneralUserInput(2);
		System.out.println("\n---To deposit money, enter 1----\n---To withdraw money, enter 2---\n");
		int option = GetUserInput.userInputInt();
		switch (option) {
			case 1:
				System.out.println("--------Deposit Money--------\n");
				System.out.println("Please Enter the sum to deposit.");
				moneySum = GetUserInput.userInputDouble();
				moneyCheck(moneySum);
				System.out.printf("Name: " + userName + " Account Number: " + userAccount + " Password: " + userPassword
						+ " Currency Type: " + selectedCurrency + " Sum: " + moneySum + "\n");
				msg = client.depositMoney(userName, userAccount, userPassword, selectedCurrency, moneySum);
				break;
			case 2:
				System.out.println("--------Withdraw Money--------\n");
				System.out.println("Please Enter the sum to withdraw.");
				moneySum = GetUserInput.userInputDouble();
				moneyCheck(moneySum);
				System.out.printf("Name: " + userName + " Account Number: " + userAccount + " Password: " + userPassword
						+ " Currency Type: " + selectedCurrency + " Sum: " + moneySum + "\n");
				msg = client.withdrawMoney(userName, userAccount, userPassword, selectedCurrency, moneySum);
				break;
		}
		return msg;
	}

	// monitor updates for a time period
	public void monitorUpdates() {
		System.out.println("--------Monitor Updates--------\n");
		System.out.println("Please enter the monitor interval time: ");
		int monitorIntervalTime = GetUserInput.userInputInt();
		System.out.println("Monitor Interval Time: " + monitorIntervalTime);
		client.monitorUpdates(monitorIntervalTime);

		// get the system time in nano seconds
		long monitorStartTime = System.nanoTime();
		// while the updates are within the period of time
		while (((System.nanoTime() - monitorStartTime) / 1e9) <= monitorIntervalTime) {
			// get the updates from server
		}
		System.out.println("Monitoring Period ended...");
	}

	// transfer balance to another account
	// function 1
	public Message transferMoney() {
		System.out.println("--------Transfer Money--------\n");
		getGeneralUserInput(2);
		System.out.println("Please Enter the Account of Recipient: ");
		int recipentAcc = GetUserInput.userInputInt();
		System.out.println("Please Enter amount to transfer: ");
		double transAmount = GetUserInput.userInputDouble();
		System.out.printf("Name: " + userName + " Account Number: " + userAccount + " Password: " + userPassword
				+ " Currency Type: " + selectedCurrency + " Recipient Account: " + recipentAcc + " Sum: " + transAmount
				+ "\n");
		Message msg = client.transferMoney(userName, userAccount, userPassword, selectedCurrency, recipentAcc, transAmount);
		return msg;
	}

	// function 2
	// check balance (Name, Password, Account Number)
	public Message checkBalance() {
		System.out.println("--------Check Balance--------\n");
		getGeneralUserInput(0);
		System.out
				.printf("Name: " + userName + " Account Number: " + userAccount + " Password: " + userPassword + "\n");
		Message msg = client.checkBalanceOfAccount(userName, userAccount, userPassword);
		return msg;

	}
}
