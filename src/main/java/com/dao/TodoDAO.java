package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.TODO_Details;

public class TodoDAO {
	private Connection conn;

	public TodoDAO(Connection conn) {
		super();
		this.conn = conn;
	}
	// ***********************************************************************************

	public boolean addTodo(String name, String todo, String status) {
		boolean f = false;
		try {
			String sql = "insert into todoapp(name,todo,status) values(?,?,?) ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, todo);
			ps.setString(3, status);

			int i = ps.executeUpdate();
			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	// ***********************************************************************************
	public List<TODO_Details> getTODO() {

		List<TODO_Details> list = new ArrayList<>();

		TODO_Details td = null;
		try {
			String sql = "select * from todoapp";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				td = new TODO_Details();
				td.setId(rs.getInt(1));
				td.setName(rs.getString(2));
				td.setTodo(rs.getString(3));
				td.setStatus(rs.getString(4));
				list.add(td);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	// ***********************************************************************************

	public TODO_Details getDeatilsById(int id) {
		TODO_Details td = null;
		try {
			String sql = "select * from todoapp where id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				td = new TODO_Details();
				td = new TODO_Details();
				td.setId(rs.getInt(1));
				td.setName(rs.getString(2));
				td.setTodo(rs.getString(3));
				td.setStatus(rs.getString(4));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return td;

	}

	// ***********************************************************************************
	public boolean updateTODO(TODO_Details td) {
		boolean f = false;
		try {
			String sql = "update todoapp set name=?,todo=?,status=? where id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, td.getName());
			ps.setString(2, td.getTodo());
			ps.setString(3, td.getStatus());
			ps.setInt(4, td.getId());

			int i = ps.executeUpdate();
			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	// ***********************************************************************************
	public boolean deleteById(int id) {
		boolean f = false;
		try {
			String sql = "delete from todoapp where id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			int i = ps.executeUpdate();
			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

}
