package com.krusmark.services;

import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.krusmark.dao.registerUserDao;
import com.krusmark.dao.registerUserDaoImpl;

public class registerUserService {
    private static registerUserDao dao = registerUserDaoImpl.getInstance();

    public static void registerUser(String ers_username, String ers_password, String user_first_name,
            String user_last_name, String user_email, InputStream is) {

        String encrypt = md5(ers_password);
        dao.registerUser(ers_username, encrypt, user_first_name, user_last_name, user_email, is);
    }

    public static String md5(String input) {

        String md5 = null;
        if (null == input)
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