package testing;
import utils.*;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class TestingGround {
    public static void main (String[] args){
        // try{
        //     String str = "Example String";
        // byte[] b = str.getBytes("UTF-8");
        // System.out.println("Array " + b);
        // System.out.println("Array as String" + Arrays.toString(b));
        // int wew = 12;
        // String s = String.valueOf(wew);
        System.out.println("This is output of StringtoByte\n" + utils.Marshal.stringToByte("wew"));
        System.out.println("This is output of BytetoString\n" + utils.Marshal.byteToString(utils.Marshal.stringToByte("wew")));

        // } catch (UnsupportedEncodingException e){
        //     System.out.println("Unsupported character set" + e);
        // }
    }
}
