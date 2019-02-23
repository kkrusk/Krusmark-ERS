package com.krusmark.dao;

import com.krusmark.models.User;

public interface LoginDao {
	public User login(String username, String password);
}
