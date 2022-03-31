package client;

import entity.Message;
import entity.Constants.CURRENCY;

//client input class will check for all the errors (invalid user inputs) and get all the user input 
public class ClientInput {
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

	// general user input get and checks for the (password, username, account number
	// and currency)
	public void getGeneralUserInput(int option) {
		// request user to input user name for account
		System.out.println("Please Enter a Name: ");
		userName = GetUserInput.userInputString();
		// account name check function checks if the user input is valid
		accountNameCheck(userName);
		// check if it is a unique name
		// check the password meets the requirements
		if (option == 1 || option == 2) {
			if (option == 2) {
				// request for account number from user
				System.out.println("Please Enter Account number: ");
				userAccount = GetUserInput.userInputInt();
				// check if account number is valid input
				accountNumberCheck(userAccount);
			}
			// request for currency type from user
			System.out.println("Please Enter Currency Type (e.g. USD, SGD, EURO): ");
			userCurrencyType = GetUserInput.userInputString();
			// check if the currency type is a valid type
			currencyCheckFunc(userCurrencyType);
			while (currencyCheck == false) {
				// if it is not valid, system will request for new user input
				System.out.println("Please insert a valid currency type");
				userCurrencyType = GetUserInput.userInputString();
				// will check again
				currencyCheckFunc(userCurrencyType);
			}
		} else {
			// request for account number
			System.out.println("Please Enter Account number: ");
			userAccount = GetUserInput.userInputInt();
			accountNumberCheck(userAccount);

		}
		// request for password from user
		System.out.println("Please Enter Password: ");
		userPassword = GetUserInput.userInputString();
		// check if the password is correct
		accountPasswordCheck(userPassword);
	}

	// if the user decides to open a new account, this function will be called
	public Message openNewAccount() {
		// user will be request to input username, password and currencytype
		getGeneralUserInput(1);
		// this will print out the user input to show the user
		System.out.printf(
				"Your Input: Name: " + userName + " Password: " + userPassword + " Currency Type: " + userCurrencyType);
		// will create a request message
		Message msg = client.createAccount(userName, userPassword, selectedCurrency);
		return msg;
	}

	// this function will check if the currency type is a valid type and if it
	// exists
	public void currencyCheckFunc(String newAccountCurr) {
		currencyCheck = false;
		// check against the currencies available
		for (CURRENCY currency : allCurrency) {
			if (currency.toString().equals(newAccountCurr.toUpperCase())) {
				currencyCheck = true;
				selectedCurrency = currency;
			}
		}
	}

	// account name (alphabets)
	// check if account name is a valid name
	public void accountNameCheck(String newAccountName) {
		accountNameCheck = false;
		while (accountNameCheck == false) {
			// check if string consists of just alphabets
			if ((userName != null) && (!userName.equals("")) && (userName.matches("^[a-zA-Z]*$"))) {
				accountNameCheck = true;
			} else {
				System.out.println("Please Enter a valid account name (alphabets only): ");
				userName = GetUserInput.userInputString();

			}
		}
	}

	// less than 9 digits number int
	// check if the account number is valid
	public void accountNumberCheck(int newAccountNumber) {
		accountCheck = false;
		while (accountCheck == false) {
			// check if the account number is more than equals to 10 digits
			if (String.valueOf(userAccount).length() >= 10) {
				System.out.println("Please insert a valid account number (<10 digits): ");
				userAccount = GetUserInput.userInputInt();
				// how to check for existing account number?
			} else {
				accountCheck = true;
			}
		}
	}

	// 8 alphanumeric length string
	// check if the account password is valid
	public void accountPasswordCheck(String newPassword) {
		passwordCheck = false;
		while (passwordCheck == false) {
			// check if account password only consists of alphabets and numbers
			if (userPassword.length() == 8 && userPassword.matches("^[a-zA-Z0-9]*$") && (!userPassword.equals(""))) {
				passwordCheck = true;
			} else {
				System.out.println("Please Enter a valid Password (8 alphanumeric password): ");
				userPassword = GetUserInput.userInputString();
			}
		}
	}

	// check if the balance input by server is valid
	public void moneyCheck(double values) {
		moneyCheck = false;
		while (moneyCheck == false) {
			System.out.println(moneySum);
			// ensure that balance is not a negative number
			if (moneySum > 0) {
				moneyCheck = true;
			} else {
				System.out.println("Please Enter a valid sum.");
				moneySum = GetUserInput.userInputDouble();
			}
		}
	}

	// close account function
	public Message closeExistingAccount() {
		// request for input
		getGeneralUserInput(0);
		System.out.printf("Your Input: Name: " + userName + " Account Number: " + userAccount + " Password: "
				+ userPassword + "\n");
		Message msg = client.closeAccount(userName, userAccount, userPassword);
		// return request message to send to server
		return msg;
	}

	// function to deposit or withdraw money from account
	public Message depositOrWithdraw() {
		Message msg = null;
		System.out.println("------Deposit or Withdraw------\n");
		// request for user input for username user account number user password
		// currency
		getGeneralUserInput(2);
		// request user to choose the preferred service to either deposit money or
		// withdraw money
		System.out.println("\n---To deposit money, enter 1----\n---To withdraw money, enter 2---\n");
		int option = GetUserInput.userInputInt();
		switch (option) {
			case 1:
				// request for balance from user
				System.out.println("--------Deposit Money--------\n");
				System.out.println("Please Enter the sum to deposit.");
				moneySum = GetUserInput.userInputDouble();
				moneyCheck(moneySum);
				System.out.printf("Your Input: Name: " + userName + " Account Number: " + userAccount + " Password: "
						+ userPassword
						+ " Currency Type: " + selectedCurrency + " Sum: " + moneySum + "\n");
				msg = client.depositMoney(userName, userAccount, userPassword, selectedCurrency, moneySum);
				break;
			case 2:
				// request for balance input
				System.out.println("--------Withdraw Money--------\n");
				System.out.println("Please Enter the sum to withdraw.");
				moneySum = GetUserInput.userInputDouble();
				// check if the balance is valid
				moneyCheck(moneySum);
				// show the user their input
				System.out.printf("Your Input: Name: " + userName + " Account Number: " + userAccount + " Password: "
						+ userPassword
						+ " Currency Type: " + selectedCurrency + " Sum: " + moneySum + "\n");
				// generate request message
				msg = client.withdrawMoney(userName, userAccount, userPassword, selectedCurrency, moneySum);
				break;
		}
		// return request message
		return msg;
	}

	// monitor updates for a time period
	public Message monitorUpdates() {
		System.out.println("--------Monitor Updates--------\n");
		System.out.println("Please enter the monitor interval time: ");
		// request for user input
		double monitorIntervalTime = GetUserInput.userInputDouble();
		System.out.println("Monitor Interval Time: " + monitorIntervalTime + " seconds");
		System.out.println("--------Starting monitoring--------");
		Message m = client.monitorUpdates(monitorIntervalTime);
		return m;
	}

	// end monitor updates service to send to server to notify ending of service
	public Message endMonitorUpdates() {
		Message m = client.endMonitorUpdates();
		return m;
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
		System.out.printf("Your Input: Name: " + userName + " Account Number: " + userAccount + " Password: "
				+ userPassword
				+ " Currency Type: " + selectedCurrency + " Recipient Account: " + recipentAcc + " Sum: " + transAmount
				+ "\n");
		// generate message to send to server
		Message msg = client.transferMoney(userName, userAccount, userPassword, selectedCurrency, recipentAcc,
				transAmount);
		// return message
		return msg;
	}

	// function 2
	// check balance (Name, Password, Account Number)
	public Message checkBalance() {
		System.out.println("--------Check Balance--------\n");
		getGeneralUserInput(0);
		System.out
				.printf("Your Input: Name: " + userName + " Account Number: " + userAccount + " Password: "
						+ userPassword + "\n");
		// generate request message
		Message msg = client.checkBalanceOfAccount(userName, userAccount, userPassword);
		return msg;

	}
}
