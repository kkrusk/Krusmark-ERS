package com.krusmark.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.krusmark.db.Database;
import com.krusmark.models.User;

public class LoginDaoImpl implements LoginDao {

	private static LoginDaoImpl instance;

	private LoginDaoImpl() {}

	public static LoginDaoImpl getInstance() {
		if (instance == null) {
			instance = new LoginDaoImpl();
		}
		return instance;
	}

	@Override
	public User login(String username, String password) {
		User rtnUser = null;

		try {
			Connection conn = Database.getConnection();
			System.out.println("sending data (" + username + "," + password + ")");
			System.out.println("Conn established: " + conn.toString());
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ers_users WHERE (ers_username = ? or user_email = ?) and ers_password = ?");
			//			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM reginfo WHERE username = ? and password = ?");
			stmt.setString(1, username);
			stmt.setString(2, username);
			stmt.setString(3, password);
			ResultSet rs = stmt.executeQuery();
			System.out.println("Checking username and password.. (" + rs.getFetchSize() + ")");
			if (rs.getFetchSize() > 1) { System.err.println("Warning! Two or more users with the same username and password."); }
			if (rs.next()) {
				rtnUser = new User(
						rs.getInt("ers_users_id"),
						rs.getString("ers_username"),
						rs.getString("ers_password"),
						rs.getString("user_first_name"),
						rs.getString("user_last_name"),
						rs.getString("user_email"),
						rs.getInt("user_role_id")
						);
			}
			conn.close();
		} catch (SQLException sqle) {
			sqle.getMessage();
			System.err.println(sqle.getSQLState());
			System.err.println(sqle.getErrorCode());
		}
		return rtnUser;
	}
}
