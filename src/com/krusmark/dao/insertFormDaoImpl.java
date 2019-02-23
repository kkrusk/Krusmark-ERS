package com.krusmark.dao;

import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.krusmark.db.Database;

public class insertFormDaoImpl implements insertFormDao {

	private static insertFormDaoImpl instance;

	private insertFormDaoImpl() {
	}

	public static insertFormDaoImpl getInstance() {
		if (instance == null) {
			instance = new insertFormDaoImpl();
		}
		return instance;
	}

	@Override
	public void insertForm(double amount, String description, InputStream receipt, int author, int type) {

		try {
			Connection conn = Database.getConnection();
			System.out.println("Conn established: " + conn.toString());

			CallableStatement stmt = conn.prepareCall("{ CALL submitReimbursement(?,?,?,?,?) }");

			stmt.setDouble(1, amount);
			stmt.setString(2, description);
			stmt.setBlob(3, receipt); // NOT CORRECT
			stmt.setInt(4, author);
			stmt.setInt(5, type);

			stmt.executeQuery();
			conn.close();

		} catch (SQLException sqle) {
			sqle.getMessage();
			System.err.println(sqle.getSQLState());
			System.err.println(sqle.getErrorCode());
		}
	}
}
