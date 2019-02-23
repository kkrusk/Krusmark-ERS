package com.krusmark.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jasypt.encryption.pbe.StandardPBEBigIntegerEncryptor;

public class encrypter extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String str = req.getParameter("s");
		
		PrintWriter pw = resp.getWriter();
		
		BigInteger myNumber = new BigInteger(str);
		
		StandardPBEBigIntegerEncryptor encryptor = new StandardPBEBigIntegerEncryptor();
		encryptor.setPassword("vinkle");                     // we HAVE TO set a password

		BigInteger myEncryptedNumber = encryptor.encrypt(myNumber);

		pw.print(myEncryptedNumber);

	}

}
