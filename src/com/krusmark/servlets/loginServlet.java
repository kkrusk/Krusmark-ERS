package com.krusmark.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.krusmark.models.User;
import com.krusmark.services.LoginService;

public class loginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(req.getQueryString());

		String username = req.getParameter("un");
		String password = req.getParameter("pw");

//		System.out.println("Running threads..");
//		Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
//		Thread[] threadArray = threadSet.toArray(new Thread[threadSet.size()]);
//		for (Thread t : threadArray)
//			System.out.println(t.toString());

		System.out.println("Checking login..");

		User user = LoginService.login(username, password);

		if (user != null) {
			System.out.println("Login successful!");
			HttpSession session = req.getSession();
			session.setAttribute("usr", user);
			//req.getRequestDispatcher("main.jsp").forward(req, resp);
			resp.sendRedirect("main.jsp");
		} else {
			System.err.println("Bad username and/or password");
			req.setAttribute("error", "Invalid Username or Password");
			RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
			rd.include(req, resp);

		}
	}
}