package com.krusmark.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.krusmark.db.Database;
import com.krusmark.models.UserStatistic;

public class genReportDaoImpl implements genReportDao {

	private static genReportDaoImpl instance;

	private genReportDaoImpl() {
	}

	public static genReportDaoImpl getInstance() {
		if (instance == null) {
			instance = new genReportDaoImpl();
		}
		return instance;
	}

	@Override
	public List<UserStatistic> genReport() {
		List<UserStatistic> list = new ArrayList<>();
		try {
			Connection conn = Database.getConnection();

			CallableStatement stmt = conn.prepareCall("{ CALL generateReport() }");
			stmt.executeQuery();
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(new UserStatistic(rs.getString("Name"), rs.getInt("Pending"), rs.getInt("Approved"), rs.getInt("Denied")));
			}
			conn.close();
		} catch (SQLException sqle) {
			sqle.getMessage();
			System.err.println(sqle.getSQLState());
			System.err.println(sqle.getErrorCode());
		}
		return list;
	}

}
