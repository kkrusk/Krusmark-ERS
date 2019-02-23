package com.krusmark.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.krusmark.models.Reimbursement;
import com.krusmark.models.User;
import com.krusmark.services.ReimbursementService;

public class reimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public reimbursementServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Reimbursement> table = ReimbursementService.pull(Integer.parseInt((String) request.getAttribute("id")), Integer.parseInt((String) request.getAttribute("role")));
	
		User u = (User) request.getSession(false).getAttribute("usr");
//		id='datatable' 
		String output = "<table class='table table-hover table-expandable table-striped table-bordered'>";
		
		output += "<thead><tr><th>" + "Amount" + "</th><th>" + "Date Submitted" + "</th><th>" + "Date Resolved"
				+ "</th><th>" + "Author" + "</th><th>" + "Resolver" + "</th><th>" + "Type" + "</th><th>" + "Status"
				+ "</th></tr>" + "</thead><tbody>";

		for (Reimbursement r : table) {
			output += "<tr><td>$" + r.getAmount() + "</td><td>" + r.getSubmitted() + "</td><td>" + ((r.getResolved()!=null)?r.getResolved():"-")
					+ "</td><td>" + r.getAuthor() + "</td><td>" + ((r.getResolver()!=null)?r.getResolver():"-") + "</td><td>" + r.getType()
					+ "</td><td>" + r.getStatus() + "</td></tr>";

			output += "<tr><td colspan='8'>"
					+ "<dl class='row'>"
					+ "<dt class='col-sm-2'>Description</dt>"
					+ "<dd class='col-sm-10'>" + r.getDescription() + "</dd>"
					+ "</dl><hr><dl class='row'>"
					+ "<dt class='col-sm-2'>Actions</dt>"
					+ "<dd class='col-sm-10'>" //start of buttons 
					+ genBtnForm(r,u)
					+ "</dd>"
					+ "</td></tr>";
		}

		output += "</tbody></table>";

		request.setAttribute("reimbtable", output);
		RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
		rd.include(request, response);
	}
	
	public String acceptBtn(Reimbursement r, User u) {
		return "<button type='submit' name='status' value='2' class='btn btn-success mr-2'>Success</button>";
	}

	public String denyBtn(Reimbursement r, User u) {
		return "<button type='submit' name='status' value='3' class='btn btn-danger mr-2'>Deny</button>";
	}
	
	private String genBtnForm(Reimbursement r, User u) {
		String receipt = "<button type='button' class='btn btn-info mr-2' data-toggle='modal' data-target='#myModal'>Receipt</button>";
		
		String out = "";
		
		out += "<form action='update' method='post'>"
				+ "<input type='hidden' name='resolver_id' value='" + u.getId() + "'>"
				+ "<input type='hidden' name='reimb_id' value='" + r.getId() + "'>"
				+ ((r.getStatus().equals("Pending"))?acceptBtn(r,u)+denyBtn(r,u)+receipt:receipt)
				+ "</form>";

		//System.out.println(out);
		
		return out;
	}

}