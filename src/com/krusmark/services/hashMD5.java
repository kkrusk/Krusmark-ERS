package com.krusmark.services;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class hashMD5 {
	private static hashMD5 instance;

	private hashMD5() {
	}

	public static hashMD5 getInstance() {
		if (instance == null)
			instance = new hashMD5();
		return instance;
	}
	
	public static String md5(String input) {
        String md5 = null;
        
        if (input == null)
            return null;

        try {
            // Create MessageDigest object for MD5
            MessageDigest digest = MessageDigest.getInstance("MD5");
            // Update input string in message digest
            digest.update(input.getBytes(), 0, input.length());
            // Converts message digest value in base 16 (hex)
            md5 = new BigInteger(1, digest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return md5;
    }
}
