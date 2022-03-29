package utils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.lang.Math;

import javax.xml.crypto.Data;
import java.net.SocketException;
import java.net.UnknownHostException;
import utils.Marshal;

import entity.Constants;

public class Connections {

    public static void sendMsgToServer(String messagetosend, DatagramSocket clientSocket, String serverIp) {
        try {
        	InetAddress ip = InetAddress.getByName(serverIp);
            byte[] buf = null;
            buf = Marshal.stringToByte(messagetosend);
            DatagramPacket dpmsg = new DatagramPacket(buf, buf.length, ip, Constants.serverPortNumber);
            clientSocket.send(dpmsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendMsgToClient(String messagetosend, DatagramPacket clientPacket) {
        try {
            InetAddress clientAddr = clientPacket.getAddress();
            int clientPort = clientPacket.getPort();
            DatagramSocket msg = new DatagramSocket();
            byte buf[] = null;
            // System.out.println("Sending Message To Client: " + messagetosend);
            buf = Marshal.stringToByte(messagetosend);
            DatagramPacket dpmsg = new DatagramPacket(buf, buf.length, clientAddr, clientPort);
            msg.send(dpmsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String clientToReceive(DatagramSocket clientSocket) {
        String result = null;
        try {
            byte[] recvbuffer = new byte[Constants.messageLength];
            DatagramPacket dataRecv = new DatagramPacket(recvbuffer, Constants.messageLength);
            clientSocket.receive(dataRecv);
            String recvdString = Marshal.byteToString(recvbuffer);
            result = recvdString;
            System.out.println(recvdString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // public static void serverListen(Queue<DatagramPacket> packetReceived) { // prolly need Queue<datagrampacket>
    //                                                                         // packetreceive = new linkedlist<>();
    //     try {
    //         DatagramSocket socketToListen = new DatagramSocket(Constants.serverPortNumber);
    //         byte[] recvBuffer = new byte[Constants.messageLength];
    //         do {
    //             DatagramPacket incomingPacket = new DatagramPacket(recvBuffer, Constants.messageLength);
    //             socketToListen.receive(incomingPacket);
    //             packetReceived.add(incomingPacket);
    //             Constants.clientPort = incomingPacket.getPort();
    //             // System.out.println("cheated is " + Constants.clientPort);
    //             String recvmsg = new String(recvBuffer);
    //             System.out.println("\n" + recvmsg);
    //         } while (true);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }

    // public static int msgID() {
    //     return (Constants.messageIdentifer++);
    // }

    public static String clientAddrPort(DatagramSocket clientsocket) {
        int port = clientsocket.getLocalPort();
        InetAddress ip = null;
        try {
            ip = InetAddress.getByName(null);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String addr = ip.toString();
        String[] ipOnly = addr.split(Pattern.quote("/"));
        String addrPort = ipOnly[1] + "|" + port;
        //System.out.println("addrport is : " + addrPort);
        return addrPort;
    }

    public static DatagramSocket clientSocketPort(DatagramSocket socket) {
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return socket;
    }

}