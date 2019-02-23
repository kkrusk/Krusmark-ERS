package com.krusmark.dao;

import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.krusmark.db.Database;

public class registerUserDaoImpl implements registerUserDao {

	private static registerUserDaoImpl instance;

	private registerUserDaoImpl() {
	}

	public static registerUserDaoImpl getInstance() {
		if (instance == null) {
			instance = new registerUserDaoImpl();
		}
		return instance;
	}

	@Override
	public void registerUser(String ers_username, String ers_password, String user_first_name, String user_last_name,
			String user_email, InputStream is) {

		try {
			Connection conn = Database.getConnection();
			System.out.println("Conn established: " + conn.toString());

			CallableStatement stmt = conn.prepareCall("{ CALL registerUser(?,?,?,?,?,?) }");

			stmt.setString(1, ers_username);
			stmt.setString(2, ers_password);
			stmt.setString(3, user_first_name); // NOT CORRECT
			stmt.setString(4, user_last_name);
			stmt.setString(5, user_email);
			stmt.setBlob(6, is);

			stmt.executeQuery();
			conn.close();

		} catch (SQLException sqle) {
			sqle.getMessage();
			System.err.println(sqle.getSQLState());
			System.err.println(sqle.getErrorCode());
		}
	}
}
