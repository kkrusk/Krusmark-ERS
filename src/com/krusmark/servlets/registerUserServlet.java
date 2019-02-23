package com.krusmark.servlets;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.krusmark.services.registerUserService;

public class registerUserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(req.getQueryString());
		
		System.out.println(System.getProperty("user.dir"));
		
		InputStream is = new BufferedInputStream(new FileInputStream(new File(req.getServletContext().getRealPath("img/noprofile.png"))));
		
		String ers_username = req.getParameter("un");
		String ers_password = req.getParameter("pw");
		String user_first_name = req.getParameter("fn");
		String user_last_name = req.getParameter("ln");
		String user_email = req.getParameter("em");

		registerUserService.registerUser(ers_username, ers_password, user_first_name, user_last_name, user_email, is);
		
        resp.setContentType("text/html");  
        req.setAttribute("success","Successful account creation!");        
        req.getRequestDispatcher("index.jsp").include(req, resp);  
	}
}