package com.krusmark.services;

import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.krusmark.dao.userUpdateDao;
import com.krusmark.dao.userUpdateDaoImpl;

public class userUpdateService {
	private static userUpdateDao dao = userUpdateDaoImpl.getInstance();

	public static void userUpdate(int id, String username, String password, String firstname, String lastname, String email, InputStream userImage) {
		String encrypt = md5(password);
		dao.userUpdate(id, username, encrypt, firstname, lastname, email, userImage);
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
