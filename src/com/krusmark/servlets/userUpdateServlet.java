package com.krusmark.servlets;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.krusmark.models.User;
import com.krusmark.services.userUpdateService;

@MultipartConfig
public class userUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getQueryString());

        int id = ((User) req.getSession().getAttribute("usr")).getId();

        String username = req.getParameter("un");
        String password = req.getParameter("pw");
        String firstname = req.getParameter("fn");
        String lastname = req.getParameter("ln");
        String email = req.getParameter("em");
        InputStream inputStream = null; // input stream of the upload file

        // obtains the upload file part in this multipart request
        Part filePart = req.getPart("userImage");

        if (filePart != null) {
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());

            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
            
            if (inputStream.available()==0)
            	inputStream = null;
        }

        userUpdateService.userUpdate(id, username, password, firstname, lastname, email, inputStream);

        // req.getRequestDispatcher("main.jsp").forward(req, resp);
        resp.sendRedirect("main.jsp");
    }
}