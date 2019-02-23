package com.krusmark.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.krusmark.db.Database;

public class updateStatusDaoImpl implements updateStatusDao {

	private static updateStatusDaoImpl instance;

	private updateStatusDaoImpl() {
	}

	public static updateStatusDaoImpl getInstance() {
		if (instance == null) {
			instance = new updateStatusDaoImpl();
		}

		return instance;
	}

	@Override
	public void updateStatus(int status, int resolver_id, int reimb_id) {
		try {
			Connection conn = Database.getConnection();
			System.out.println("Conn established: " + conn.toString());

			System.out.println("in updateStatus: " + status + " " + resolver_id + " " + reimb_id);
			
			CallableStatement stmt = conn.prepareCall("{ CALL updateStatus(?,?,?) }");

			stmt.setInt(1, status);
			stmt.setInt(2, resolver_id);
			stmt.setInt(3, reimb_id);

			System.out.println(stmt.toString());
			
			stmt.executeQuery();
			conn.close();

		} catch (SQLException sqle) {
			sqle.getMessage();
			System.err.println(sqle.getSQLState());
			System.err.println(sqle.getErrorCode());
		}
	}
}