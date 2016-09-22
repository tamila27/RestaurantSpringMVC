package com.goit.gojavaonline.spring.mvc.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class PasswordHash {

    public static String hash(String value) {
        String result;
        try {
            MessageDigest digest = MessageDigest.getInstance( "MD5");
            byte[] hash = digest.digest(value.getBytes());
            result = toHexadecimal(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private static String toHexadecimal(byte hash[]) {
        StringBuilder buf = new StringBuilder(hash.length * 2);
        int i;
        for (i = 0; i < hash.length; i++) {
            if (((int) hash[i] & 0xff) < 0x10) {
                buf.append("0");
            }
            buf.append(Long.toString((int) hash[i] & 0xff, 16));
        }
        return buf.toString();
    }
}