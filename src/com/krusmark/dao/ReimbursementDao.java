package com.krusmark.dao;

import java.util.List;

import com.krusmark.models.Reimbursement;

public interface ReimbursementDao {
	public List<Reimbursement> getReimbursement();
	public List<Reimbursement> getReimbursement(String userid);
}
