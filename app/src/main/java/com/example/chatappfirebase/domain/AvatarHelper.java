package com.example.chatappfirebase.domain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AvatarHelper {

    private final static String GAVATAR_URL = "http://www.gravatar.com/avatar/";

    public static String getAvatarUrl(String username){
        return GAVATAR_URL + md5(username) + "?s=72";
    }
    public static final String md5(final String s){

        final String MD5 = "MD5";

        try {
            MessageDigest digest = MessageDigest.getInstance(MD5);
            digest.update(s.getBytes());
            byte [] messageDigestArr = digest.digest();

            StringBuilder hexString = new StringBuilder();

            for (byte aMessageDigest : messageDigestArr){
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while(h.length() < 2)
                    h = "0"+h;
                hexString.append(h);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
