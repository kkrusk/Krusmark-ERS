package com.krusmark.services;

import java.util.List;

import com.krusmark.dao.ReimbursementDao;
import com.krusmark.dao.ReimbursementDaoImpl;
import com.krusmark.models.Reimbursement;

public class ReimbursementService {
	private static ReimbursementDao dao = ReimbursementDaoImpl.getInstance();

	public static List<Reimbursement> pull(int id, int role) {
		if (role == 1)
			return dao.getReimbursement();
		else if (role == 0)
			return dao.getReimbursement(id+"");
		else
			return null;
	}
}
