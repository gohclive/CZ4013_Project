package main;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.util.Scanner;

import client.*;
import entity.Constants;
import entity.Message;
import utils.Connections;

public class MainFunction {
	private boolean exit = false;

	public static void main(String[] args) throws IOException {
		MainFunction main = new MainFunction();
		main.mainMenu();
	}

	public void mainMenu() {
		
		Scanner sc =  new Scanner(System.in);
		
		System.out.println("pinging server...");
		InetAddress ip = null;
		boolean done = false;
		while(!done){
			try {
				System.out.println("Please Enter Server Ip Address: ");
				String serverIp = sc.next();
				ip = InetAddress.getByName(serverIp);
				done = true;
			} catch (UnknownHostException e) {
				System.out.println("invalid IP address!");
			}
		}
		
		Client c = new Client(ip, Constants.clientPort);
		ClientInput clientInput = new ClientInput(c);
		
		DatagramSocket clientSocket = null;
		
		clientSocket = Connections.clientSocketPort(clientSocket);
		//clientSocket = Connections.setSocketTimeout(clientSocket);
		Message msg = c.ping();
		Connections.sendMsgToServer(msg, clientSocket, ip.toString());
		
		int count = 0;
		while(count <= Constants.retry) {
			try {
				clientSocket.setSoTimeout(Constants.requestTimeout);
				Connections.clientToReceive(clientSocket);
			}
			catch(SocketException e){
				//resend
				count++;
				Connections.sendMsgToServer(msg, clientSocket, ip.toString());
			}
		}
		if (count == 3) {
			System.out.println("Server not online!");
		}
		 
		
		printMenu(0);
		while (exit == false) {
			int userInput = GetUserInput.userInputInt();
			Message m = null;
			switch (userInput) {
				case 1:
					m = clientInput.openNewAccount();
					Connections.sendMsgToServer(m, clientSocket, ip.toString());
					break;
				case 2:
					m = clientInput.closeExistingAccount();
					break;
				case 3:
					m = clientInput.depositOrWithdraw();
					break;
				case 4:
					clientInput.monitorUpdates();
					break;
				case 5:
					m = clientInput.transferMoney();
					break;
				case 6:
					m = clientInput.checkBalance();
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
		// printval = 1 means it only wants to reprint the option list
		if (printVal == 1) {
			System.out.print(MENU);
		} else {
			System.out.print(MenuTitle + MENU);
		}

	}
}