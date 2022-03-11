package utils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;
import javax.xml.crypto.Data;
import java.net.SocketException;
import java.net.UnknownHostException;
import utils.Marshal;

import entity.Constants;

public class Connections {
    
    public static void sendMsgToServer (String messagetosend, DatagramSocket clientSocket){
        try {
            //DatagramSocket msg = new DatagramSocket(); // need to run this on client side and enter it for param
            byte buf[]= null;
            System.out.println("Sending Message To Server: "+ messagetosend);
            buf = messagetosend.getBytes();
            DatagramPacket dpmsg = new DatagramPacket(buf, buf.length, Constants.serverIP, Constants.serverPortNumber);
            clientSocket.send(dpmsg);
        } catch(Exception e){ e.printStackTrace( ); }
    }

    public static void sendMsgToClient (String messagetosend, DatagramPacket clientPacket ){
        try {
        InetAddress clientAddr = clientPacket.getAddress();
        int clientPort = clientPacket.getPort();
        DatagramSocket msg = new DatagramSocket();
        byte buf[]= null;
        System.out.println("Sending Message To Client: "+ messagetosend);
        buf = messagetosend.getBytes();
        DatagramPacket dpmsg = new DatagramPacket(buf, buf.length, clientAddr, clientPort);
        msg.send(dpmsg);
        } catch(Exception e){ e.printStackTrace( ); }
    }

    public static void clientToReceive (DatagramSocket clientSocket){
        try {
            byte[] recvbuffer = new byte[Constants.messageLength];
            DatagramPacket dataRecv = new DatagramPacket(recvbuffer, Constants.messageLength);
            clientSocket.receive(dataRecv);
            String recvdString = new String (recvbuffer);
            System.out.println("\n" + recvdString);
        } catch(Exception e){ e.printStackTrace( ); }
    }

    public static void serverListen (Queue<DatagramPacket> packetReceived){ // prolly need Queue<datagrampacket> packetreceive = new linkedlist<>();
        try{
            DatagramSocket socketToListen = new DatagramSocket(Constants.serverPortNumber);
            byte[] recvBuffer = new byte[Constants.messageLength];
            do 
            {
                DatagramPacket incomingPacket =  new DatagramPacket(recvBuffer, Constants.messageLength);
                socketToListen.receive(incomingPacket);
                packetReceived.add(incomingPacket);
                Constants.clientPort = incomingPacket.getPort();
                System.out.println("cheated is " + Constants.clientPort);
                String recvmsg = new String(recvBuffer);
                System.out.println("\n" + recvmsg);
            }while (true);
        }catch(Exception e){ e.printStackTrace(); }
    }

    public static int msgID(){
        return (Constants.messageIdentifer++);
    }

    public static String clientAddrPort(DatagramSocket clientsocket){
        int port = clientsocket.getLocalPort();
        System.out.println("port is : "+ port);
        String wew = String.valueOf(port);
        InetAddress ip= null;
        try {
            ip = InetAddress.getByName(null);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String addr = ip.toString();
        System.out.println("addr is : "+ addr);
        String addrPort = addr + "|" + port;
        System.out.println("addrport is : "+ addrPort);
        return wew;
    }

}