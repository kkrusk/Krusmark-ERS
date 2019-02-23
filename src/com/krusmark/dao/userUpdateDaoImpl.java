package com.krusmark.dao;

import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.krusmark.db.Database;

public class userUpdateDaoImpl implements userUpdateDao {

	private static userUpdateDaoImpl instance;

	private userUpdateDaoImpl() {
	}

	public static userUpdateDaoImpl getInstance() {
		if (instance == null) {
			instance = new userUpdateDaoImpl();
		}

		return instance;
	}

	@Override
	public void userUpdate(int id, String username, String password, String firstname, String lastname, String email, InputStream userImage) {
		try {
			Connection conn = Database.getConnection();
			System.out.println("Conn established: " + conn.toString());

			CallableStatement stmt;
			if (userImage==null)
				stmt = conn.prepareCall("{ CALL updateUserNoPic(?,?,?,?,?,?) }");
			else
				stmt = conn.prepareCall("{ CALL updateUser(?,?,?,?,?,?,?) }");
		
			stmt.setInt(1, id);
			stmt.setString(2, username);
			stmt.setString(3, password);
			stmt.setString(4, firstname);
			stmt.setString(5, lastname);
			stmt.setString(6, email);
			
			if (userImage != null)
				stmt.setBlob(7,  userImage);

			stmt.executeQuery();
			conn.close();

		} catch (SQLException sqle) {
			sqle.getMessage();
			System.err.println(sqle.getSQLState());
			System.err.println(sqle.getErrorCode());
		}
	}
}
