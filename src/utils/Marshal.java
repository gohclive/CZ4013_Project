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
    public static byte[] stringToByte(String toConvert, Integer rand) {
        byte[] converting = toConvert.getBytes(Constants.commonCharset);
        return converting;
    }

    public static String byteToString(byte[] toConvert) {
        String converting = new String(toConvert, Constants.commonCharset);
        return converting;
    }

    public static String[] decodeMessage(String clientInput){
        String[] token = clientInput.split("\\|");
        return token;
    }

    public static String[] decodeForServer(String clientInput){
        String[] token = clientInput.split(":");
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
