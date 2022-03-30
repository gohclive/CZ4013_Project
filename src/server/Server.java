package server;

import main.MainFunction;
import utils.Connections;
import utils.Marshal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import entity.Account;
import entity.Constants;
import entity.Constants.CURRENCY;
import java.util.Queue;
import java.util.Random;
import java.util.ArrayList;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Queue;

public class Server {

	public static Account CreateAccount(int num, String newAccountName, String newAccountPass,
			Constants.CURRENCY newAccountCurr) {
		// create new account with name, password, currency type, inital account balance
		Account a = new Account(num, newAccountName, newAccountPass, 0.0, newAccountCurr);
		return a;
	}

	public static Account checkAccount(ArrayList<Account> accList, String accNum) {
		// close account with account number, name and password
		Account a = null;
		for (Account acc : accList) {
			if (acc.getNumber() == Integer.parseInt(accNum)) {
				a = acc;
			}
		}
		return a;
	}

	public static void initateBankAccounts(ArrayList<Account> accList) {
		// add initial bank accounts
		Account alice = new Account(123153411, "AliceAng", "1234abcd", 50.0, CURRENCY.SGD);
		Account bob = new Account(126385000, "BobLim", "abcd1234", 100.0, CURRENCY.SGD);
		Account charlie = new Account(129726338, "CharlieTan", "hello123", 50.0, CURRENCY.USD);
		accList.add(alice);
		accList.add(bob);
		accList.add(charlie);

	}

	public static void main(String[] args) throws IOException, SocketException {

		System.out.println("Starting Server...");
		System.out.println("intitalising bank accounts...");
		System.out.println("Server Listing on port " + entity.Constants.serverPortNumber + "...");

		// variables
		ArrayList<Account> accList = new ArrayList<Account>();
		ArrayList<DatagramPacket> broadcastList = new ArrayList<DatagramPacket>();
		HashMap<Integer, String> msgCache = null;
		HashMap<Integer, String> resultcache = new HashMap<Integer, String>();
		// hashmap for at-most-once semantics
		Constants.AT_MOST_ONCE = true;
		if (Constants.AT_MOST_ONCE) {
			msgCache = new HashMap<Integer, String>();
		}

		initateBankAccounts(accList);
		int accountnum = 000000000;

		try {
			DatagramSocket ds = new DatagramSocket(Constants.serverPortNumber); // make socket and bind it to port
			byte[] receive = new byte[Constants.messageLength]; // buffer to receive msg
			Queue<DatagramPacket> receiveQueue = new LinkedList<>();
			while (true) {
				DatagramPacket DpReceive = null; // make packet to receive datagrampacket
				if (getRand() >= Constants.PACKETLOSTPROB) {
					DpReceive = new DatagramPacket(receive, receive.length); // packe to read buffer and length of
																				// buffer
					ds.receive(DpReceive); // to receive the msg
					receiveQueue.add(DpReceive); // add to queue
					if (!receiveQueue.isEmpty()) {
						// System.out.println("Queue is not empty!");
						DpReceive = receiveQueue.remove();
						receive = DpReceive.getData();
						if (receiveQueue.isEmpty()) {
							// System.out.println("Queue is empty!");
						}
					}
				} else {
					System.out.println("SIMULATED PACKET LOSS, SERVER IGNORE PACKET");

				}

				if (DpReceive != null) {
					String decodedMsg = Marshal.byteToString(receive);
					String[] idcontent = Marshal.decodeForServer(decodedMsg);
					String[] message = Marshal.decodeMessage(idcontent[1]);
					String msg = "";
					Account a = null;
					if (msgCache != null) {
						if (msgCache.containsKey(Integer.parseInt(idcontent[0]))) {
							System.out.println("Duplicated Message ID: " + idcontent[0] + " is Received!");
							String result = resultcache.get(Integer.parseInt(idcontent[0]));
							msg = result;
							System.out.println("Sending Following Message to Client: " + msg);
							Connections.sendMsgToClient(msg, DpReceive);
							continue;
						} else {
							msgCache.put(Integer.parseInt(idcontent[0]), idcontent[1]);
							System.out.println("I Have inputted id content 0 " + idcontent[0] + " and idcontent 1 "
									+ idcontent[1]);
						}

					}

					switch (Integer.parseInt(message[0])) {
						case 0:
							break;
						case 1:
							// create account
							a = CreateAccount(accountnum, message[1], message[2], CURRENCY.valueOf(message[3]));
							accList.add(a);
							accountnum++;
							msg = "1|SUCCESS|" + "Account successfully created, your account number is "
									+ Marshal.accPadding(a.getNumber());
							resultcache.put(Integer.parseInt(idcontent[0]), msg);
							Connections.sendMsgToClient(msg, DpReceive);
							break;
						case 2:
							// close account
							a = checkAccount(accList, message[2]);
							if (a == null) {
								msg = "2" + Constants.ACCOUNTNOTFOUND;
							} else if (!a.getName().equals(message[1])) {
								msg = "2" + Constants.INCORRECTNAME;
							} else if (!a.getPassword().equals(message[3])) {
								msg = "2" + Constants.INCORRECTPASSWORD;
							} else {
								if (a.getBalance() >= 1) {
									msg = "2|ERROR|"
											+ "Account still have balance left! Please withdraw all amount before closing your account"
											+ "|";
								} else {
									accList.remove(a);
									msg = "2|SUCCESS|" + Marshal.accPadding(a.getNumber()) + " has been closed!|";
								}
							}
							resultcache.put(Integer.parseInt(idcontent[0]), msg);
							Connections.sendMsgToClient(msg, DpReceive);
							break;
						case 3:
							// deposit account
							a = checkAccount(accList, message[2]);
							if (a == null) {
								msg = "3" + Constants.ACCOUNTNOTFOUND;
							} else if (!a.getName().equals(message[1])) {
								msg = "3" + Constants.INCORRECTNAME;
							} else if (!a.getPassword().equals(message[3])) {
								msg = "3" + Constants.INCORRECTPASSWORD;
							} else {
								// TODO currency conversion
								int i = accList.indexOf(a);
								double bal = accList.get(i).getBalance();
								accList.get(i).setBalance(bal + Double.parseDouble(message[5]));
								msg = "3|SUCCESS|" + a.getNumber() + " balance is " + accList.get(i).getBalance() + "|";
							}
							resultcache.put(Integer.parseInt(idcontent[0]), msg);
							Connections.sendMsgToClient(msg, DpReceive);

							break;
						case 4:
							// withdraw account
							a = checkAccount(accList, message[2]);
							if (a == null) {
								msg = "4" + Constants.ACCOUNTNOTFOUND;
							} else if (!a.getName().equals(message[1])) {
								msg = "4" + Constants.INCORRECTNAME;
							} else if (!a.getPassword().equals(message[3])) {
								msg = "4" + Constants.INCORRECTPASSWORD;
							} else {
								if (a.getBalance() < Double.parseDouble(message[5])) {
									msg = "4" + Constants.INSUFFICIENTBAL;
								} else {
									int i = accList.indexOf(a);
									double bal = accList.get(i).getBalance();
									accList.get(i).setBalance(bal - Double.parseDouble(message[5]));
									msg = "4|SUCCESS|" + "your account " + a.getNumber() + ": balance is "
											+ accList.get(i).getBalance() + "|";
								}
							}
							resultcache.put(Integer.parseInt(idcontent[0]), msg);
							Connections.sendMsgToClient(msg, DpReceive);
							break;
						case 5:
							// transfer money
							Account owner = checkAccount(accList, message[2]);
							Account receiver = checkAccount(accList, message[5]);
							if (owner == null) {
								msg = "5" + Constants.ACCOUNTNOTFOUND;
							} else if (!owner.getName().equals(message[1])) {
								msg = "5" + Constants.INCORRECTNAME;
							} else if (!owner.getPassword().equals(message[3])) {
								msg = "5" + Constants.INCORRECTPASSWORD;
							} else {
								if (receiver == null) {
									msg = "5" + "|ERROR|" + "Transfer recipient account not found!|";
								} else {
									// update owner account
									int i = accList.indexOf(owner);
									double bal = accList.get(i).getBalance();
									int j = accList.indexOf(receiver);
									if (bal >= Double.parseDouble(message[6])) {
										accList.get(i).setBalance(bal - Double.parseDouble(message[6]));
										// update receiver account
										bal = accList.get(j).getBalance();
										accList.get(j).setBalance(bal + Double.parseDouble(message[6]));
										msg = "6|SUCCESS|Transfer Successful! Your balance is "
												+ accList.get(i).getBalance() + "|";
									} else {
										msg = "5" + "|ERROR|" + "Not enough Balance for transfer!|";
									}

								}
							}
							resultcache.put(Integer.parseInt(idcontent[0]), msg);
							Connections.sendMsgToClient(msg, DpReceive);

							break;
						case 6:
							// check balance
							a = checkAccount(accList, message[2]);
							if (a == null) {
								msg = "6" + Constants.ACCOUNTNOTFOUND;
							} else if (!a.getName().equals(message[1])) {
								msg = "6" + Constants.INCORRECTNAME;
							} else if (!a.getPassword().equals(message[3])) {
								msg = "6" + Constants.INCORRECTPASSWORD;
							} else {
								int i = accList.indexOf(a);
								double bal = accList.get(i).getBalance();
								msg = "2|SUCCESS|" + "Your account " + a.getNumber() + ": balance is "
										+ accList.get(i).getBalance() + "|";
							}
							Connections.sendMsgToClient(msg, DpReceive);
							break;
						case 7:
							// add broadcast
							broadcastList.add(DpReceive);

							break;
						case 8:
							for (DatagramPacket datagramPacket : broadcastList) {
								if (datagramPacket.getAddress() == DpReceive.getAddress()) {
									broadcastList.remove(datagramPacket);
								}
							}
							break;

						default:
							System.out.println("invalid input");
							msg = "invalid";
							break;
					}
					// Exit the server if the client sends "bye"
					if (Marshal.byteToString(receive).equals("bye")) {
						System.out.println("Client sent bye.....EXITING");
						break;
					}

					if (!broadcastList.isEmpty()) {
						for (int i = 0; i < broadcastList.size(); i++) {
							// System.out.println("i" + i);
							// System.out.println("broadcastList.get(i)" + broadcastList.get(i));
							Connections.sendMsgToClient(msg, broadcastList.get(i));
						}
					}

					// Clear the buffer after every message.
					receive = new byte[65535];
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static StringBuilder data(byte[] a) {
		if (a == null)
			return null;
		StringBuilder ret = new StringBuilder();
		int i = 0;
		while (a[i] != 0) {
			ret.append((char) a[i]);
			i++;
		}
		return ret;
	}

	public static int getRand() {
		Random r = new Random();
		return r.nextInt(10);
	}

	public static double convertCurrency(int value, String valuecurrency, String Acccurrency){
		double result = 0.0;
		if (valuecurrency.equalsIgnoreCase("sgd")){
			if (Acccurrency.equalsIgnoreCase("usd")){
				result = value * Constants.SGDUSD;
			}
			else if (Acccurrency.equalsIgnoreCase("euro")){
				result = value * Constants.SGDEURO;
			}
		}
		else if (valuecurrency.equalsIgnoreCase("usd")){
			if (Acccurrency.equalsIgnoreCase("sgd")){
				result = value * Constants.USDSGD;
			}
			else if (Acccurrency.equalsIgnoreCase("euro")){
				result = value * Constants.USDEURO;
			}

		}
		else if (valuecurrency.equalsIgnoreCase("euro")){
			if (Acccurrency.equalsIgnoreCase("sgd")){
				result = value * Constants.EUROSGD;
			}
			else if (Acccurrency.equalsIgnoreCase("USD")){
				result = value * Constants.EUROUSD;
			}
		}
		return result;
	}

}
