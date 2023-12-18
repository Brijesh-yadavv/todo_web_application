package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.TodoDAO;
import com.db.DBConnect;
import com.entity.TODO_Details;

@WebServlet("/delete")
public class Delete_servlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		TodoDAO dao = new TodoDAO(DBConnect.getConn());
		boolean f=dao.deleteById(id);
		HttpSession session = req.getSession();
		if (f) {
			session.setAttribute("sucMsg", "Deleted Successfully");
			resp.sendRedirect("index.jsp");
		} else {
			session.setAttribute("failed", " Deletion Failed..Something went wrong");
			resp.sendRedirect("index.jsp");
		}

	}
}
