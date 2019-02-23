package com.krusmark.servlets;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.Blob;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jasypt.encryption.pbe.StandardPBEBigIntegerEncryptor;

import com.krusmark.services.retrieveImageService;

@MultipartConfig
public class retrieveImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BigInteger rencryption = null;
		BigInteger uencryption = null;
		if (req.getParameterMap().containsKey("r"))
			rencryption = new BigInteger(req.getParameter("r"));
		if (req.getParameterMap().containsKey("u"))
			uencryption = new BigInteger(req.getParameter("u"));

		if ((rencryption!=null) ^ (uencryption!=null)) {
			StandardPBEBigIntegerEncryptor encryptor = new StandardPBEBigIntegerEncryptor();
			encryptor.setPassword("vinkle"); // we HAVE TO set a password
	
			BigInteger eid;
			Blob b = null;
			
			if (rencryption!=null) {
				System.out.println("Trying to decrypt reimbursement: " + rencryption);
				eid = encryptor.decrypt(rencryption);
				b = retrieveImageService.retrieveImage(eid.intValue());
			} else if (uencryption!=null) {
				System.out.println("Trying to decrypt user: " + uencryption);
				eid = encryptor.decrypt(uencryption);
				b = retrieveImageService.retrieveUserImage(eid.intValue());				
			}
	
			if (b != null) {
				System.out.println("b is NOT null");
				byte[] image = null;
				try {
					image = b.getBytes(1, (int) b.length());
					resp.getOutputStream().write(image);
				} catch (SQLException e) {
					e.getMessage();
				}
			}
		}
	}
}