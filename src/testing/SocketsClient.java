package testing;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

import entity.Constants;
import utils.Connections;
import utils.Marshal;

public class SocketsClient {
	public static void main(String args[]) throws IOException {
		DatagramSocket rocket = null; // instantiate a socket
		try {
			rocket = Connections.clientSocketPort(rocket); // bind socket to a port
			String msg = "1|clivegoh|12345678|SGD|";
			Connections.sendMsgToServer(msg, rocket); // send msg
			Connections.clientToReceive(rocket); // receive msg from server
			
			//deposit
			msg = "3|clivegoh|1131411111|12345678|USD|40.0|";
			Connections.sendMsgToServer(msg, rocket); // send msg
			Connections.clientToReceive(rocket); // receive msg from server
			
			//withdraw
			msg = "4|clivegoh|1131411111|12345678|USD|20.0|";
			Connections.sendMsgToServer(msg, rocket); // send msg
			Connections.clientToReceive(rocket); // receive msg from server
			
			//transfer
			msg = "5|clivegoh|1131411111|12345678|USD|1263850000|20.0|";
			Connections.sendMsgToServer(msg, rocket); // send msg
			Connections.clientToReceive(rocket); // receive msg from server

			//check balance
			msg = "6|clivegoh|1131411111|12345678|";
			Connections.sendMsgToServer(msg, rocket); // send msg
			Connections.clientToReceive(rocket); // receive msg from server
			
			
			msg = "2|clivegoh|1131411111|12345678|";
			Connections.sendMsgToServer(msg, rocket); // send msg
			Connections.clientToReceive(rocket); // receive msg from server

		} catch (Exception e) {
			e.printStackTrace();
		}

//        String s = "";
//        Scanner sc = new Scanner(System.in);
//        while(!s.equals("end")){
//        	s = sc.nextLine();
//        	Connections.sendMsgToServer(s.toString(), rocket); // send msg
//        }
//        sc.close();
	}
}