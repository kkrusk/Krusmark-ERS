package com.krusmark.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.krusmark.models.User;
import com.krusmark.services.updateStatusService;

public class updateStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(req.getQueryString());

		int status = Integer.parseInt(req.getParameter("status"));
		//int resolver_id = Integer.parseInt(req.getParameter("resolver_id"));
		int resolver_id = ((User) req.getSession().getAttribute("usr")).getId();
		int reimb_id = Integer.parseInt(req.getParameter("reimb_id"));

		System.out.println("CHECKING MFs: " + status + " " + resolver_id + " " + reimb_id);
		
		updateStatusService.updateStatus(status, resolver_id, reimb_id);

		resp.sendRedirect("main.jsp");

		//req.getRequestDispatcher("main.jsp").forward(req, resp);
	}
}
