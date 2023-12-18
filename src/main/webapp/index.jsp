<%@page import="com.dao.TodoDAO"%>
<%@page import="com.entity.TODO_Details"%>
<%@page import="com.db.DBConnect"%>
<%@page import="java.sql.Connection"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@include file="components/all_css.jsp"%>
</head>
<body>
	<%@include file="components/navbar.jsp"%>

	<h1 class="text-center text-success">TODO-App</h1>

	<%
	String msge = (String) session.getAttribute("sucMsg");
	if (msge != null) {
	%>
	<div class="alert alert-success text-center" role="alert"><%=msge%></div>
	<%
	session.removeAttribute("sucMsg");
	}
	%>
	<%
	String failed = (String) session.getAttribute("failed");
	if (failed != null) {
	%>
	<div class="alert alert-danger text-center" role="alert"><%=failed%></div>
	<%
	session.removeAttribute("failed");
	}
	%>
	<div class="container">

		<table class="table table-striped " border="1px">
			<thead class="bg-success text-white">
				<tr>
					<th scope="col">ID</th>
					<th scope="col">Name</th>

					<th scope="col">ToDo</th>
					<th scope="col">Status</th>
					<th scope="col">Action</th>
				</tr>
			</thead>
			<tbody>
				<%
				TodoDAO dao = new TodoDAO(DBConnect.getConn());
				List<TODO_Details> todo = dao.getTODO();
				for (TODO_Details t : todo) {
				%>
				<tr>
					<th scope="row"><%=t.getId()%></th>
					<th scope="col"><%=t.getName()%></th>

					<td><%=t.getTodo()%></td>
					<td><%=t.getStatus()%></td>
					<td><a href="edit.jsp?id=<%=t.getId()%>"
						class="btn btn-sm btn-success" name="id">Edit</a> <a
						href="delete?id=<%=t.getId()%>" class="btn btn-sm btn-danger"
						name="id">Delete</a></td>
				</tr>

				<%
				}
				%>

			</tbody>
		</table>


		<a class="btn btn-sm  btn-dark" href="add_todo.jsp">Add More Tasks</a>


	</div>


</body>
</html>