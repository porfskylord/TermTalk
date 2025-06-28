package com.lordscave.shared;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {
    public static String hashPassword(String password){
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encoded = digest.digest(password.getBytes());
            StringBuilder hex = new StringBuilder();
            for (byte b : encoded) {
                hex.append(String.format("%02x", b));
            }
            return hex.toString();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Hashing algorithm not available.");
            return null;
        }
    }

    public static boolean verifyPassword(String rawPassword, String hashedPassword){
        String hashedInput = hashPassword(rawPassword);
        return hashedInput != null && hashedInput.equals(hashedPassword);
    }

}
