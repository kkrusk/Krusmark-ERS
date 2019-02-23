package com.krusmark.servlets;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.krusmark.models.User;
import com.krusmark.services.insertFormService;

@MultipartConfig
public class insertFormServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getQueryString());

        double amount = Double.parseDouble(req.getParameter("amount"));
        String description = req.getParameter("description");

        InputStream inputStream = null; // input stream of the upload file

        // obtains the upload file part in this multipart request
        Part filePart = req.getPart("receipt");

        if (filePart != null) {
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());

            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
            
            if (inputStream.available()==0)
            	inputStream = new BufferedInputStream(new FileInputStream(new File(req.getServletContext().getRealPath("img/noimage.png"))));
        }

        int author = ((User) req.getSession().getAttribute("usr")).getId(); // Author ID
        int type = Integer.parseInt(req.getParameter("type"));

        insertFormService.insertForm(amount, description, inputStream, author, type);
		resp.sendRedirect("main.jsp");
//        req.getRequestDispatcher("main.jsp").forward(req, resp);
    }
}