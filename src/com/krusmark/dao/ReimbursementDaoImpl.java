package com.krusmark.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.krusmark.db.Database;
import com.krusmark.models.Reimbursement;

public class ReimbursementDaoImpl implements ReimbursementDao {

	private static ReimbursementDaoImpl instance;

	private ReimbursementDaoImpl() {
	}

	public static ReimbursementDaoImpl getInstance() {
		if (instance == null) {
			instance = new ReimbursementDaoImpl();
		}
		return instance;
	}

	@Override
	public List<Reimbursement> getReimbursement() {
		List<Reimbursement> list = new ArrayList<>();

		try {
			Connection conn = Database.getConnection();
//			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ers_reimbursement");
			CallableStatement stmt = conn.prepareCall("{ CALL selectAllReimbursements() }");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(new Reimbursement(rs.getInt("ID"), rs.getDouble("Amount"),
						rs.getDate("Submitted"), rs.getDate("Resolved"), rs.getString("Employee_Name"),
						rs.getString("Resolver_Name"), rs.getString("Reimbursement_Status"), rs.getString("Reimbursement_Type"),
						rs.getString("reimb_description"), rs.getBlob("Receipt")));
			}
			conn.close();
		} catch (SQLException sqle) {
			sqle.getMessage();
			System.err.println(sqle.getSQLState());
			System.err.println(sqle.getErrorCode());
		}
		return list;
	}

	@Override
	public List<Reimbursement> getReimbursement(String userid) {
		List<Reimbursement> list = new ArrayList<>();

		try {
			Connection conn = Database.getConnection();
			CallableStatement stmt = conn.prepareCall("{ CALL selectReimbursementsByUser(?) }");
			stmt.setString(1, userid);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(new Reimbursement(rs.getInt("ID"), rs.getDouble("Amount"),
						rs.getDate("Submitted"), rs.getDate("Resolved"), rs.getString("Employee_Name"),
						rs.getString("Resolver_Name"), rs.getString("Reimbursement_Status"), rs.getString("Reimbursement_Type"),
						rs.getString("reimb_description"), rs.getBlob("Receipt")));
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
