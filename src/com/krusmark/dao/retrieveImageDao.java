package com.krusmark.dao;

import java.sql.Blob;

public interface retrieveImageDao {
	public Blob retrieveImage(int reimb_id);

	public Blob retrieveUserImage(int ers_users_id);
}
