package com.guli.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

    public static String encode(String str){

        try {
            char[] hex ={'0','1','2','3','4','5','6','7','8','9','a','b'
                    ,'c','d','e','f'};
            byte[] bytes=str.getBytes();
            MessageDigest md=MessageDigest.getInstance("MD5");
            md.update(bytes);
            bytes=md.digest();
            int j=bytes.length;
            char[] chars=new char[j*2];
            int k=0;
            for (byte b : bytes) {
                chars[k++] = hex[b >>> 4 & 0xf];
                chars[k++] = hex[b & 0xf];
            }
            return new String(chars);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException(e+"加密出错");
        }
    }
}
