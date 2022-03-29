package utils;
import java.util.Random;
import entity.Constants;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Marshal {
    // java use big endian by default
    // integer numbers, strings, boolean, currency(to string since it has tostring
    // function),
    // need make for single variable and structure argument
    public static byte[] stringToByte(String toConvert, int rand) {
        String msgID = String.valueOf(rand) + ":"; 
        toConvert = msgID + toConvert;
        byte[] converting = toConvert.getBytes(Constants.commonCharset);
        return converting;
    }

    public static String byteToString(byte[] toConvert) {
        String converting = new String(toConvert, Constants.commonCharset);
        return converting;
    }

    public static byte[] intToByte(int toConvert) {
        byte[] converting = ByteBuffer.allocate(4).putInt(toConvert).array();
        return converting;
    }

    public static int bytetoInt(byte[] toConvert) {
        int converting = ByteBuffer.wrap(toConvert).getInt();
        return converting;
    }

    public static String[] decodeMessage(String clientInput){
        String[] token = clientInput.split("\\|");
        // for (int i=0;i<=clientInput.length();i++){
        //     System.out.println(token[i]);
        // }
        return token;
    }

    public static String[] decodeForServer(String clientInput){
        String[] token = clientInput.split(":");
        // for (int i=0;i<=clientInput.length();i++){
        //     System.out.println(token[i]);
        // }
        return token;
    }

    public static String accPadding (int accNo){
        String padding = String.format("%09d", accNo);
        return padding;
    }

    public static int unpadAcc (String paddedAcc){
        int unpad = Integer.valueOf(paddedAcc);
        return unpad;
    }

    public static int msglength(byte[] bytetostrip){
        String length = byteToString(bytetostrip);
        return length.length();
    }
    
    

}
