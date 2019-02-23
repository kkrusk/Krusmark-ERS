package com.krusmark.dao;

import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.krusmark.db.Database;

public class retrieveImageDaoImpl implements retrieveImageDao {

	private static retrieveImageDaoImpl instance;

	private retrieveImageDaoImpl() {
	}

	public static retrieveImageDaoImpl getInstance() {
		if (instance == null) {
			instance = new retrieveImageDaoImpl();
		}

		return instance;
	}

	@Override
	public Blob retrieveImage(int reimb_id) {
		Blob blob = null;
		try {
			Connection conn = Database.getConnection();
			System.out.println("Conn established: " + conn.toString());

			CallableStatement stmt = conn.prepareCall("{ CALL retrieveImage(?) }");

			stmt.setInt(1, reimb_id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next())
				blob = rs.getBlob("reimb_receipt");

			conn.close();

		} catch (SQLException sqle) {
			sqle.getMessage();
			System.err.println(sqle.getSQLState());
			System.err.println(sqle.getErrorCode());
		}
		return blob;
	}
	
	@Override
	public Blob retrieveUserImage(int ers_users_id) {
		Blob blob = null;
		try {
			System.out.println("ers_users_id" + ers_users_id);
			Connection conn = Database.getConnection();
			System.out.println("Conn established: " + conn.toString());

			CallableStatement stmt = conn.prepareCall("{ CALL retrieveUserImage(?) }");

			stmt.setInt(1, ers_users_id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next())
				blob = rs.getBlob("user_image");

			conn.close();

		} catch (SQLException sqle) {
			sqle.getMessage();
			System.err.println(sqle.getSQLState());
			System.err.println(sqle.getErrorCode());
		}
		return blob;
	}
}
