package com.app.ecommerce.utility;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashPassword {
    public static String hashPasswordWithMD5(String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] digest = messageDigest.digest(password.getBytes());
        return DatatypeConverter.printHexBinary(digest).toUpperCase();
    }
}
