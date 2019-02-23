package com.krusmark.services;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.krusmark.dao.LoginDao;
import com.krusmark.dao.LoginDaoImpl;
import com.krusmark.models.User;

public class LoginService {
    private static LoginDao dao = LoginDaoImpl.getInstance();

    public static User login(String username, String password) {
        String encrypt = md5(password);
        return dao.login(username, encrypt);
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