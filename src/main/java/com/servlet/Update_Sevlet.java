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

@WebServlet("/update")
public class Update_Sevlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("idd"));
		String name = req.getParameter("name");
		String todo = req.getParameter("todo");
		String status = req.getParameter("status");

		TodoDAO dao = new TodoDAO(DBConnect.getConn());
		TODO_Details td = new TODO_Details();
		td.setId(id);
		td.setName(name);
		td.setTodo(todo);
		td.setStatus(status);

		boolean f = dao.updateTODO(td);
		HttpSession session = req.getSession();
		if (f) {
			session.setAttribute("sucMsg", "Todo Updated Successfully");
			resp.sendRedirect("index.jsp");
		} else {
			session.setAttribute("failed", " Updation Failed..Something went wrong");
			resp.sendRedirect("index.jsp");
		}
	}
}
