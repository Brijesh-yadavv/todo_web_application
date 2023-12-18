package com.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.TodoDAO;
import com.db.DBConnect;

@WebServlet("/add_TODO")
public class Add_TODO extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name=req.getParameter("name");
		String todo=req.getParameter("todo");
		String status=req.getParameter("status");
		
		TodoDAO dao=new TodoDAO(DBConnect.getConn());
		boolean f=dao.addTodo(name, todo, status);
		HttpSession session=req.getSession();
		
		
		if(f) {
//			System.out.println("added succesfully");
			session.setAttribute("sucMsg", "Todo Added Successfully");
			
			resp.sendRedirect("index.jsp");
		}
		else {
			session.setAttribute("failed", "Failed Someting went wrong");
			resp.sendRedirect("index.jsp");
		}
	}

}
