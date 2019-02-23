package com.krusmark.services;

import com.krusmark.dao.updateStatusDao;
import com.krusmark.dao.updateStatusDaoImpl;

public class updateStatusService {
	private static updateStatusDao dao = updateStatusDaoImpl.getInstance();

	public static void updateStatus(int status, int resolver_id, int reimb_id) {
		dao.updateStatus(status, resolver_id, reimb_id);
	}
}