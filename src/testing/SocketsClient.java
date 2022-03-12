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
            Connections.sendMsgToServer("this is test", rocket); // send msg
            Connections.clientToReceive(rocket); // receive msg from server
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}