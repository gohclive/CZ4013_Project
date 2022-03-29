package testing;
import utils.*;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Scanner;

public class TestingGround {
    public static void main (String[] args){
        // try{
        //     String str = "Example String";
        // byte[] b = str.getBytes("UTF-8");
        // System.out.println("Array " + b);
        // System.out.println("Array as String" + Arrays.toString(b));
        // int wew = 12;
        // String s = String.valueOf(wew);
        // System.out.println("This is output of StringtoByte\n" + utils.Marshal.stringToByte("wew"));
        // System.out.println("This is output of BytetoString\n" + utils.Marshal.byteToString(utils.Marshal.stringToByte("wew")));
        // } catch (UnsupportedEncodingException e){
        //     System.out.println("Unsupported character set" + e);
        // }
        // Scanner sc = new Scanner(System.in);
        // System.out.println("enter currency: ");
        // String input = sc.nextLine();
        
        // String inputo = "5|clivegoh|1231411111|SGD|1231111111|20.0|";
        // byte [] test = Marshal.stringToByte(inputo);
        // String decoded = Marshal.byteToString(test);
        // String[] wowie = Marshal.decodeForServer(decoded);
        
        // System.out.println(wowie[3].equals("SGD"));
        // System.out.println(wowie[3].equals(input));

        // for (int i=0;i<wowie.length;i++){
        //     System.out.println(wowie[i]);
        // }
        // System.out.println(wowie.length);
        
        // int topad = 1234;
        // String padded = String.format("%09d", topad);
        // System.out.println(padded);
        // int unpad = Integer.valueOf(padded);
        // System.out.println(unpad);
        String tez = "5|clivegoh|1131411111|12345678|USD|1263850000|20.0|";
        byte[] again = Marshal.stringToByte(tez, 1231);
        // byte[] agaragar = Marshal.stringToByte(tez);
        String recvstring = Marshal.byteToString(again);
        String[] serverDecode = Marshal.decodeForServer(recvstring);
        // String rerere = Marshal.byteToString(agaragar);
        // System.out.println(tez.length());
        // System.out.println(tez);
        System.out.println(serverDecode[0]);
        System.out.println(serverDecode[1]);
        System.out.println(recvstring);
        // System.out.println(rerere);
    }
}
