package main;

import java.io.IOException;
import java.net.DatagramSocket;
import java.sql.Connection;

import client.*;
import entity.Constants;
import utils.Connections;

public class MainFunction {
	ClientInput clientInput = new ClientInput();
	private boolean exit = false;<<<<<<<HEAD

	public static void main(String[] args) throws IOException {
		MainFunction main = new MainFunction();
	}

	public void mainMenu() {
		DatagramSocket clientSocket = null;
		clientSocket = Connections.clientSocketPort(clientSocket);
=======

	public static void main(String[] args) throws IOException {
		try {
			DatagramSocket clientDatagram = new DatagramSocket();
			switch (userInput) {
				case 1:
					try {
						Connections.sendMsgToServer("Testing joke", clientSocket);
						Connections.clientToReceive(clientSocket);
						// clientInput.openNewAccount();
						break;
					} catch (Exception e) {
						e.printStackTrace();
					}
				case 2:
					clientInput.closeExistingAccount();
					break;
				case 3:
					clientInput.depositOrWithdraw();
					break;
				case 4:
					clientInput.monitorUpdates();
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
=======
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
				"------------------------------------\n";
		// printval = 1 means it only wants to reprint the option list
		if (printVal == 1) {
			System.out.print(MENU);
		} else {
			System.out.print(MenuTitle + MENU);
		}

	}
}