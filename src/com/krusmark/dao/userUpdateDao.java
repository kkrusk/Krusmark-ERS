package com.krusmark.dao;

import java.io.InputStream;

public interface userUpdateDao {
	public void userUpdate(int id, String username, String password, String firstname, String lastname, String email, InputStream userInput);
}
