package com.krusmark.dao;

import java.io.InputStream;

public interface registerUserDao {
	public void registerUser(String ers_username, String ers_password, String user_first_name, String user_last_name,
			String user_email, InputStream is);
}
