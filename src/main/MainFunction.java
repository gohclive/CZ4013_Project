package main;

import java.io.IOException;
import java.net.DatagramSocket;

import client.*;
import utils.Connections;

public class MainFunction {
	ClientInput clientInput = new ClientInput();
	private boolean exit = false;
	public static void main(String[] args) throws IOException {

		MainFunction main = new MainFunction();
		main.mainMenu();
	}

	public void mainMenu() {

		printMenu(0);
		while(exit == false) {
			int userInput = GetUserInput.userInputInt();
			switch(userInput) {
			case 1:
				// try{
				// 	DatagramSocket msg = new DatagramSocket();
				// 	Connections.sendMsgToServer("Testing joke", msg);
				// 	Connections.clientToReceive(msg);
				// }catch(Exception e){ e.printStackTrace( ); }

				clientInput.openNewAccount();
				break;
			case 2:
				clientInput.closeExistingAccount();
				break;
			case 3: 
				clientInput.depositOrWithdraw();
				break;
			case 4: 
				clientInput.monitorUpdates();
				break;
			case 5: 
				clientInput.transferMoney();
				break;
			case 6: 
				clientInput.checkBalance();
				break;
			case 7:
				System.out.println("Turning Off System...");
				GetUserInput.closeUserInputScanner();
				System.out.print("System Off");
				System.exit(0);
			default:
				System.out.println("Invalid Input, Please Try Again.\n" + "------------------------------------\n");
				printMenu(1);
				break;
			}
			printMenu(1);
		}
	}

	public void printMenu(int printVal) {
		// menu
		String MenuTitle = "\n------------------------------------\n" +
				"Distributed Banking System\n";
		String MENU = "\n------------------------------------\n" +
				"Select an option from [1-7]:\n" +
				"1. Open a new account\n" +
				"2. Close an existing account\n" +
				"3. Deposit/Withdraw Money from account\n" +
				"4. Monitor Updates made to all bank accounts\n" +
				"5. Transfer Money to another account\n" +
				"6. Check Balance in Account\n" + 
				"7. Exit\n" +
				"------------------------------------\n" +
				"------------------------------------\n";
		//printval = 1 means it only wants to reprint the option list
		if(printVal == 1) {
			System.out.print(MENU);
		} else {
			System.out.print(MenuTitle + MENU);
		}

	}
}