package com.krusmark.services;

import java.io.IOException;
import java.io.InputStream;

import com.krusmark.dao.insertFormDao;
import com.krusmark.dao.insertFormDaoImpl;

public class insertFormService {
	private static insertFormDao dao = insertFormDaoImpl.getInstance();

	public static void insertForm(double amount, String description, InputStream receipt, int author, int type) {
		try {
			if (receipt.available()==0)
				dao.insertForm(amount, description, null, author, type);
			else
				dao.insertForm(amount, description, receipt, author, type);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
