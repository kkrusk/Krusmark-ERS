package com.krusmark.dao;

import java.io.InputStream;

public interface insertFormDao {
	public void insertForm(double amount, String description, InputStream receipt, int author, int type);

}
