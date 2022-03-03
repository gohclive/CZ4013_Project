package utils;

import entity.Constants;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Marshal
{
    //java use big endian by default
    //integer numbers, strings, boolean, currency(to string since it has tostring function), 
    //need make for single variable and structure argument
    public static byte[] stringToByte (String toConvert){
        byte[] converting = toConvert.getBytes(Constants.commonCharset);
        return converting;
    }

    public static String byteToString (byte[] toConvert){
        String converting = new String(toConvert,Constants.commonCharset);
        return converting;
    }

    public static byte[] intToByte(int toConvert){
        byte[] converting = ByteBuffer.allocate(4).putInt(toConvert).array();
        return converting;
    }

    public static int bytetoInt(byte[] toConvert){
        int converting = ByteBuffer.wrap(toConvert).getInt();
        return converting;
    }

    // public static byte[] accountToByte(){
        
    // }

}
