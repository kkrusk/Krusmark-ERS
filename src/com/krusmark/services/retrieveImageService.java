package com.krusmark.services;

import java.sql.Blob;

import com.krusmark.dao.retrieveImageDao;
import com.krusmark.dao.retrieveImageDaoImpl;

public class retrieveImageService {
	private static retrieveImageDao dao = retrieveImageDaoImpl.getInstance();

	public static Blob retrieveImage(int reimb_id) {
		return dao.retrieveImage(reimb_id);
	}
	
	public static Blob retrieveUserImage(int ers_users_id) {
		return dao.retrieveUserImage(ers_users_id);
	}
}
