package com.planon.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.planon.mvc.model.Department;
import com.planon.mvc.model.JDBCConnection;
import com.planon.mvc.model.SqlQueries;

public class Departmentdao {

	public void insertDepartment(Department department) throws SQLException, ClassNotFoundException {
		try (Connection connection = JDBCConnection.getConnection();) {
			PreparedStatement preparedStatement = connection.prepareStatement(SqlQueries.INSERT_DEPARTMENT);
			preparedStatement.setString(1, department.getDepartmentName());
			preparedStatement.setString(2, department.getDepartmentCode());
			preparedStatement.executeUpdate();
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}

	}

	public void updateDepartment(Department department) throws SQLException, ClassNotFoundException {
		try (Connection connection = JDBCConnection.getConnection();) {
			PreparedStatement preparedStatement = connection.prepareStatement(SqlQueries.UPDATE_DEPARTMENT);
			preparedStatement.setString(1, department.getDepartmentCode());
			preparedStatement.setString(2, department.getDepartmentName());
			preparedStatement.setInt(3, department.getDepartmentid());
			preparedStatement.executeUpdate();
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}

	}

	public Department selectDepartmentById(int id) throws SQLException, ClassNotFoundException {
		Department Dept = null;

		try (Connection connection = JDBCConnection.getConnection();) {
			PreparedStatement preparedStatement = connection.prepareStatement(SqlQueries.SELECT_DEPARTMENT_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Integer deptid = resultSet.getInt(1);
				String deptCode = resultSet.getString(2);
				String deptName = resultSet.getString(3);
				Dept = new Department(deptid, deptName, deptCode);
			}

			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return Dept;
	}

	public List<Department> selectAllDepartments() throws SQLException, ClassNotFoundException {
		List<Department> dept = new ArrayList<Department>();

		try (Connection connection = JDBCConnection.getConnection();) {
			PreparedStatement preparedStatement = connection.prepareStatement(SqlQueries.SELECT_ALL_DEPARTMENTS);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int deptID = resultSet.getInt(1);
				String deptCode = resultSet.getString(2);
				String deptName = resultSet.getString(3);
				dept.add(new Department(deptID, deptName, deptCode));
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return dept;
	}

	public boolean deleteDepartment(int id) throws SQLException, ClassNotFoundException {
		boolean rowDeleted = false;

		try (Connection connection = JDBCConnection.getConnection();) {
			PreparedStatement preparedStatement = connection.prepareStatement(SqlQueries.DELETE_DEPARTMENT_BY_ID);
			preparedStatement.setInt(1, id);
			rowDeleted = preparedStatement.executeUpdate() > 0;
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return rowDeleted;

	}

	public List<Department> searchAllDepartments(String data) throws SQLException, ClassNotFoundException {
		List<Department> dept = new ArrayList<Department>();

		try (Connection connection = JDBCConnection.getConnection();) {
			Statement statement = connection.createStatement();
			String query;
			if (data != null) {
				query = "select * from departmenttable where departmentcode like '%" + data
						+ "%'or Departmentname like '%" + data + "%' ";
			} else {
				query = "select * from departmenttable";
			}
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				String deptName = resultSet.getString(2);
				String deptCode = resultSet.getString(3);
				dept.add(new Department(deptName, deptCode));
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return dept;
	}

	/*
	 * public static void main(String args[]) throws ClassNotFoundException,
	 * SQLException { Departmentdao d = new Departmentdao(); Department dept = new
	 * Department("SS", "Support Service"); d.insertDepartment(dept); }
	 */

	public List<Department> sortColumns(List<Department> dept, int column) {
		switch (column) {
		case 1:
			Collections.sort(dept, (dept1, dept2) -> {

				return dept1.getDepartmentCode().compareTo(dept2.getDepartmentCode());
			});
			break;
		case 2:
			Collections.sort(dept, (dept1, dept2) -> {

				return dept2.getDepartmentCode().compareTo(dept1.getDepartmentCode());
			});
			break;
		case 4:
			Collections.sort(dept, (dept1, dept2) -> {

				return dept1.getDepartmentName().compareTo(dept2.getDepartmentName());
			});
			break;
		case 5:
			Collections.sort(dept, (dept1, dept2) -> {

				return dept2.getDepartmentName().compareTo(dept1.getDepartmentName());
			});
			break;
		}
		return dept;
	}

}
