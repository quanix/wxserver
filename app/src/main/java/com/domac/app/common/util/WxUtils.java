package com.domac.app.common.util;

import javax.servlet.ServletInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.Arrays;

/**
 * created by lihaoquan
 */
public class WxUtils {

    //从输入流读取post参数
    public static String readStreamParameter(ServletInputStream in){
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader=null;
        try{
            reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while((line = reader.readLine())!=null){
                buffer.append(line);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(null!=reader){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return buffer.toString();
    }

    public static boolean checkSignature(String token,String signature,String timestamp,String nonce) {
        String[] tmpArr={token,timestamp,nonce};
        Arrays.sort(tmpArr);
        String tmpStr=ArrayToString(tmpArr);
        tmpStr=SHA1Encode(tmpStr);
        System.out.println("SHA_1>>>>>>>>>>>>"+tmpStr);
        if(tmpStr.equalsIgnoreCase(signature)){
            return true;
        }else{
            return false;
        }
    }


    //sha1加密
    public static String SHA1Encode(String sourceString) {
        String resultString = null;
        try {
            resultString = new String(sourceString);
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            resultString = byte2hexString(md.digest(resultString.getBytes()));
        } catch (Exception ex) {
        }
        return resultString;
    }

    public static  String byte2hexString(byte[] bytes) {
        StringBuffer buf = new StringBuffer(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            if (((int) bytes[i] & 0xff) < 0x10) {
                buf.append("0");
            }
            buf.append(Long.toString((int) bytes[i] & 0xff, 16));
        }
        return buf.toString().toUpperCase();
    }

    public static String ArrayToString(String [] arr){
        StringBuffer bf = new StringBuffer();
        for(int i = 0; i < arr.length; i++){
            bf.append(arr[i]);
        }
        return bf.toString();
    }
}
