package testing;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

import javax.xml.crypto.Data;

import java.net.SocketException;
import java.net.UnknownHostException;

import utils.Constants;

public class Connections {
    
    public static void sendMsgToServer (String messagetosend) throws IOException{
        DatagramSocket msg = null;
        try {
            msg = new DatagramSocket();
        } catch (SocketException e1) {
            System.out.println("Error making Client Datasocket");
        }
        byte buf[]= null;
        System.out.println("Sending Message: "+ messagetosend);
        buf = messagetosend.getBytes();
        DatagramPacket dpmsg = new DatagramPacket(buf, buf.length, Constants.serverIP, Constants.serverPortNumber);
        try {
            msg.send(dpmsg);
        } catch (IOException e) {
            System.out.println("Error Sending Message to Server!");
        }
    }

    public static void sendMsgToClient (String messagetosend, int DestPort, String DestIP ) throws IOException{
        DatagramSocket msg = null;
        try {
            msg = new DatagramSocket();
        } catch (SocketException e1) {
            System.out.println("Error making Server Datasocket");
        }
        byte buf[]= null;
        InetAddress clientIP = null;
        try {
            clientIP = InetAddress.getByName(DestIP);
        } catch (UnknownHostException e1) {
            System.out.println("Client IP Not found!");
        }
        System.out.println("Sending Message: "+ messagetosend);
        buf = messagetosend.getBytes();
        DatagramPacket dpmsg = new DatagramPacket(buf, buf.length, clientIP, DestPort);
        try {
            msg.send(dpmsg);
        } catch (IOException e) {
            System.out.println("Error Sending Message to Client!");
        }
    }
    
    public static void Serverlisten() throws IOException{
        DatagramSocket portToListen = null;
        try {
            ServerSocket();
        } catch (SocketException e) {
            System.out.println("Server Port is not listening!");
        }
        byte[] receive = new byte[65535];
        DatagramPacket msgReceive = null;
        System.out.println("Server is Listening...");
        while(true)
        {
            msgReceive = new DatagramPacket(receive, receive.length);
            ServerSocket().receive(msgReceive);
            System.out.println("Client Says: " + data(receive));
        }
    }

    private static DatagramSocket ServerSocket() throws SocketException {
        DatagramSocket portToListen;
        return portToListen = new DatagramSocket(Constants.serverPortNumber);
    }

    public static void Clientlisten() throws IOException{
        DatagramSocket portToListen = null;
        try {
            ClientSocket();
        } catch (SocketException e) {
            System.out.println("Client Port is not listening!");
        }
        byte[] receive = new byte[65535];
        DatagramPacket msgReceive = null;
        System.out.println("Client is Listening...");
        while(true)
        {
            msgReceive = new DatagramPacket(receive, receive.length);
            ClientSocket().receive(msgReceive);
            System.out.println("Server Says: " + data(receive));
        }
    }

    private static DatagramSocket ClientSocket() throws SocketException {
        DatagramSocket portToListen;
        return portToListen = new DatagramSocket(Constants.clientPortNumber);
    }

    public static StringBuilder data(byte[] a)
    {
        if (a == null)
            return null;
        StringBuilder ret = new StringBuilder();
        int i = 0;
        while (a[i] != 0)
        {
            ret.append((char) a[i]);
            i++;
        }
        return ret;
    }
}

