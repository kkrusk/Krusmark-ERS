package com.krusmark.services;

import java.util.List;

import com.krusmark.dao.genReportDao;
import com.krusmark.dao.genReportDaoImpl;
import com.krusmark.models.UserStatistic;

public class ReportService {
	private static genReportDao dao = genReportDaoImpl.getInstance();
	
	public static List<UserStatistic> pull() {
		return dao.genReport();
	}

}
