package com.planon.mvc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.planon.mvc.model.Department;
import com.planon.mvc.model.Employee;
import com.planon.mvc.model.JDBCConnection;
import com.planon.mvc.model.SqlQueries;

//Data Access Objects -->Design pattern
//CRUD  operations
public class EmployeeDao {

	public boolean updateEmployee(Employee employee) throws ClassNotFoundException, SQLException {
		boolean rowUpdated = false;

		try (Connection connection = JDBCConnection.getConnection();) {
			PreparedStatement preparedStatement = connection.prepareStatement(SqlQueries.UPDATE_EMPLOYEE);
			preparedStatement.setInt(1, employee.getDepartment().getDepartmentid());
			preparedStatement.setString(2, employee.getFirstName());
			preparedStatement.setString(3, employee.getLastName());
			preparedStatement.setString(4, employee.getEmail());
			preparedStatement.setString(5, employee.getGender());
			preparedStatement.setDate(6, (java.sql.Date) new Date(employee.getDateOfBirth().getTime()));
			preparedStatement.setDate(7, (java.sql.Date) new Date(employee.getDateOfJoining().getTime()));
			preparedStatement.setDouble(8, employee.getSalary());
			preparedStatement.setInt(9, employee.getEmployeeId());
			rowUpdated = preparedStatement.executeUpdate() > 0;
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return rowUpdated;

	}

	// insertEmployee
	public void insertEmployee(Employee employee) throws SQLException, ClassNotFoundException {
		try (Connection connection = JDBCConnection.getConnection();) {
			PreparedStatement preparedStatement = connection.prepareStatement(SqlQueries.INSERT_EMPLOYEE);
			preparedStatement.setInt(1, employee.getEmployeeId());
			preparedStatement.setInt(2, employee.getDepartment().getDepartmentid());
			preparedStatement.setString(3, employee.getFirstName());
			preparedStatement.setString(4, employee.getLastName());
			preparedStatement.setString(5, employee.getEmail());
			preparedStatement.setString(6, employee.getGender());
			preparedStatement.setDate(7, (java.sql.Date) new Date(employee.getDateOfBirth().getTime()));
			preparedStatement.setDate(8, (java.sql.Date) new Date(employee.getDateOfJoining().getTime()));
			preparedStatement.setDouble(9, employee.getSalary());
			preparedStatement.executeUpdate();
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}

	}

	public Employee selectEmployeeById(int id) throws ClassNotFoundException, SQLException {
		Employee emp = null;
		try (Connection connection = JDBCConnection.getConnection();) {
			PreparedStatement preparedStatement = connection.prepareStatement(SqlQueries.SELECT_EMPLOYEE_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				int deptid = resultSet.getInt(2);
				String deptCode = resultSet.getString(3);
				String deptName = resultSet.getString(4);
				String firstName = resultSet.getString(5);
				String lastName = resultSet.getString(6);
				String email = resultSet.getString(7);
				String gender = resultSet.getString(8);
				Date dob = resultSet.getDate(9);
				Date doj = resultSet.getDate(10);
				Double salary = resultSet.getDouble(11);
				Department dept = new Department(deptid, deptCode, deptName);
				emp = new Employee(id, dept, firstName, lastName, email, gender, salary, dob, doj);
				System.out.println("employee department id:" + emp.getDepartment().getDepartmentid());

			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return emp;
	}

	public List<Employee> searchAllEmployee(String data) throws ClassNotFoundException, SQLException {
		List<Employee> emp = new ArrayList<Employee>();
		try (Connection connection = JDBCConnection.getConnection();) {
			Statement statement = connection.createStatement();
			String query;
			if (data != null) {
				query = "SELECT e.employeeid,e.departmentid,d.departmentcode,d.departmentname,e.firstname,e.lastname,e.email,e.gender,e.dateofbirth,e.dateofJoining,e.salary\r\n"
						+ "FROM employeetable e\r\n" + "INNER JOIN departmenttable d\r\n"
						+ "ON  e.departmentid = d.departmentid where e.employeeid like '%" + data
						+ "%' or d.departmentcode like '%" + data + "%' or d.departmentname like '%" + data
						+ "%' or e.firstname  like '%" + data + "%' or \r\n" + "e.lastname  like '%" + data
						+ "%' or e.email  like '%" + data + "%' or e.gender  like '%" + data
						+ "%' or e.dateofbirth like '%" + data + "%'" + " or e.dateofJoining  like '%" + data
						+ "%' or e.salary like '%" + data + "%'";

			} else {
				query = "select * from employeetable";
			}
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				Integer id = resultSet.getInt(1);
				Integer deptid = resultSet.getInt(2);
				String deptCode = resultSet.getString(3);
				String deptName = resultSet.getString(4);
				String firstName = resultSet.getString(5);
				String lastName = resultSet.getString(6);
				String email = resultSet.getString(7);
				String gender = resultSet.getString(8);
				Date dob = resultSet.getDate(9);
				Date doj = resultSet.getDate(10);
				Double salary = resultSet.getDouble(11);
				Department dept = new Department(deptid, deptCode, deptName);
				emp.add(new Employee(id, dept, firstName, lastName, email, gender, salary, dob, doj));
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return emp;
	}

	public List<Employee> selectAllEmployee() throws ClassNotFoundException, SQLException {
		List<Employee> emp = new ArrayList<Employee>();

		try (Connection connection = JDBCConnection.getConnection();) {
			PreparedStatement preparedStatement = connection.prepareStatement(SqlQueries.SELECT_ALL_EMPLOYEES);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Integer id = resultSet.getInt(1);
				Integer deptid = resultSet.getInt(2);
				String deptCode = resultSet.getString(3);
				String deptName = resultSet.getString(4);
				String firstName = resultSet.getString(5);
				String lastName = resultSet.getString(6);
				String email = resultSet.getString(7);
				String gender = resultSet.getString(8);
				Date dob = resultSet.getDate(9);
				Date doj = resultSet.getDate(10);
				Double salary = resultSet.getDouble(11);
				Department dept = new Department(deptid, deptCode, deptName);
				emp.add(new Employee(id, dept, firstName, lastName, email, gender, salary, dob, doj));
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return emp;
	}

	public boolean deleteEmployee(int id) throws ClassNotFoundException, SQLException {
		boolean rowDeleted = false;
		try (Connection connection = JDBCConnection.getConnection();) {
			PreparedStatement preparedStatement = connection.prepareStatement(SqlQueries.DELETE_EMPLOYEE_BY_ID);
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

	public List<Employee> employeeSQLQueries(int id1) throws ClassNotFoundException, SQLException {
		List<Employee> emp = new ArrayList<Employee>();
		String query = null;
		switch (id1) {
		case 1:
			query = SqlQueries.MAXIMUM_SALARIED_EMPLOYEES;
			break;
		case 2:
			query = SqlQueries.MINIMUM_SALARIED_EMPLOYEES;
			break;
		case 3:
			query = SqlQueries.MAXIMUM_SALARIED_EMPLOYEES_FROM_EACH_DEPARTMENT;
			break;
		case 4:
			query = SqlQueries.MINIMUM_SALARIED_EMPLOYEES_FROM_EACH_DEPARTMENT;
			break;
		}

		try (Connection connection = JDBCConnection.getConnection();) {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Integer id = resultSet.getInt(1);
				Integer deptid = resultSet.getInt(2);
				String deptCode = resultSet.getString(3);
				String deptName = resultSet.getString(4);
				String firstName = resultSet.getString(5);
				String lastName = resultSet.getString(6);
				String email = resultSet.getString(7);
				String gender = resultSet.getString(8);
				Date dob = resultSet.getDate(9);
				Date doj = resultSet.getDate(10);
				Double salary = resultSet.getDouble(11);
				Department dept = new Department(deptid, deptCode, deptName);
				emp.add(new Employee(id, dept, firstName, lastName, email, gender, salary, dob, doj));
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return emp;
	}

	public List<Employee> sortColumns(List<Employee> emp, int column) {
		switch (column) {
		case 1:
			Collections.sort(emp, (emp1, emp2) -> {
				if (emp1.getEmployeeId() > emp2.getEmployeeId())
					return 1;
				else if (emp1.getEmployeeId() < emp2.getEmployeeId())
					return -1;
				return 0;
			});
			break;

		case 3:
			Collections.sort(emp, (emp1, emp2) -> {

				return emp1.getDepartment().getDepartmentCode().compareTo(emp2.getDepartment().getDepartmentCode());
			});
			break;
		case 5:
			Collections.sort(emp, (emp1, emp2) -> {

				return emp1.getDepartment().getDepartmentName().compareTo(emp2.getDepartment().getDepartmentName());
			});
			break;
		case 7:
			Collections.sort(emp, (emp1, emp2) -> {

				return emp1.getFirstName().compareTo(emp2.getFirstName());
			});
		case 9:
			Collections.sort(emp, (emp1, emp2) -> {

				return emp1.getLastName().compareTo(emp2.getLastName());
			});
			break;
		case 11:
			Collections.sort(emp, (emp1, emp2) -> {

				return emp1.getEmail().compareTo(emp2.getEmail());
			});
			break;
		case 13:
			Collections.sort(emp, (emp1, emp2) -> {

				return emp1.getGender().compareTo(emp2.getGender());
			});
			break;
		case 15:
			Collections.sort(emp, (emp1, emp2) -> {

				if (emp1.getDateOfBirth().before(emp2.getDateOfBirth())) {
					return -1;
				} else if (emp1.getDateOfBirth().after(emp2.getDateOfBirth())) {
					return 1;
				}
				return 0;
			});
			break;

		case 17:
			Collections.sort(emp, (emp1, emp2) -> {

				if (emp1.getDateOfJoining().before(emp2.getDateOfJoining())) {
					return -1;
				} else if (emp1.getDateOfJoining().after(emp2.getDateOfJoining())) {
					return 1;
				}
				return 0;
			});
			break;
		case 19:
			Collections.sort(emp, (emp1, emp2) -> {
				if (emp1.getSalary() > emp2.getSalary()) {
					return 1;
				} else if (emp1.getSalary() < emp2.getSalary()) {
					return -1;
				}
				return 0;
			});
			break;
		case 2:
			Collections.sort(emp, (emp1, emp2) -> {
				if (emp1.getEmployeeId() < emp2.getEmployeeId())
					return 1;
				else if (emp1.getEmployeeId() > emp2.getEmployeeId())
					return -1;
				return 0;
			});
			break;

		case 4:
			Collections.sort(emp, (emp1, emp2) -> {

				return emp2.getDepartment().getDepartmentCode().compareTo(emp1.getDepartment().getDepartmentCode());
			});
			break;
		case 6:
			Collections.sort(emp, (emp1, emp2) -> {

				return emp2.getDepartment().getDepartmentName().compareTo(emp1.getDepartment().getDepartmentName());
			});
			break;
		case 8:
			Collections.sort(emp, (emp1, emp2) -> {

				return emp2.getFirstName().compareTo(emp1.getFirstName());
			});
		case 10:
			Collections.sort(emp, (emp1, emp2) -> {

				return emp2.getLastName().compareTo(emp1.getLastName());
			});
			break;
		case 12:
			Collections.sort(emp, (emp1, emp2) -> {

				return emp2.getEmail().compareTo(emp1.getEmail());
			});
			break;
		case 14:
			Collections.sort(emp, (emp1, emp2) -> {

				return emp2.getGender().compareTo(emp1.getGender());
			});
			break;
		case 16:
			Collections.sort(emp, (emp1, emp2) -> {

				if (emp1.getDateOfBirth().before(emp2.getDateOfBirth())) {
					return 1;
				} else if (emp1.getDateOfBirth().after(emp2.getDateOfBirth())) {
					return -1;
				}
				return 0;
			});
			break;

		case 18:
			Collections.sort(emp, (emp1, emp2) -> {

				if (emp1.getDateOfJoining().before(emp2.getDateOfJoining())) {
					return 1;
				} else if (emp1.getDateOfJoining().after(emp2.getDateOfJoining())) {
					return -1;
				}
				return 0;
			});
			break;
		case 20:
			Collections.sort(emp, (emp1, emp2) -> {
				if (emp1.getSalary() < emp2.getSalary()) {
					return 1;
				} else if (emp1.getSalary() > emp2.getSalary()) {
					return -1;
				}
				return 0;
			});
			break;

		}
		return emp;
	}

	public List<Employee> maxSalary(List<Employee> emp) {

		double maxSalary = emp.stream().map(e -> e.getSalary()).max(Double::compare).get();
		List<Employee> emplist = emp.stream().filter(e -> e.getSalary() == maxSalary).collect(Collectors.toList());
		return emplist;

	}

	public List<Employee> minSalary(List<Employee> emp) {
		double minSalary = emp.stream().map(e -> e.getSalary()).min(Double::compare).get();
		List<Employee> emplist2 = emp.stream().filter(e -> e.getSalary() == minSalary).collect(Collectors.toList());
		return emplist2;

	}

	public List<Employee> maxSalaryForEachDepartment(List<Employee> emp) {
		Map<Integer, List<Employee>> groupedByDepartment = emp.stream()
				.collect(Collectors.groupingBy(e -> e.getDepartmentid()));

		List<Employee> result = new ArrayList<>();
		for (Integer deptId : groupedByDepartment.keySet()) {
			List<Employee> departments = new ArrayList<>(groupedByDepartment.get(deptId));
			double maxSalary = 0.0;
			for (int i = 0; i < departments.size(); i++)
				maxSalary = Math.max(maxSalary, departments.get(i).getSalary());
			for (int i = 0; i < departments.size(); i++)
				if (departments.get(i).getSalary() == maxSalary)
					result.add(departments.get(i));
		}
		return result;

	}

	public List<Employee> minSalaryForEachDepartment(List<Employee> emp) {
		Map<Integer, List<Employee>> groupedByDepartment = emp.stream()
				.collect(Collectors.groupingBy(e -> e.getDepartmentid()));

		List<Employee> result = new ArrayList<>();
		for (Integer deptId : groupedByDepartment.keySet()) {
			List<Employee> departments = new ArrayList<>(groupedByDepartment.get(deptId));
			double minSalary = Double.MAX_VALUE;
			for (int i = 0; i < departments.size(); i++)
				minSalary = Math.min(minSalary, departments.get(i).getSalary());
			for (int i = 0; i < departments.size(); i++)
				if (departments.get(i).getSalary() == minSalary)
					result.add(departments.get(i));
		}
		return result;

	}

	public void deleteMultiple(ArrayList<Integer> idList) throws ClassNotFoundException, SQLException {
		try (Connection connection = JDBCConnection.getConnection();) {
			StringBuffer queryBuffer = new StringBuffer("DELETE FROM employeetable where employeeid IN (");
			PreparedStatement preparedStatement = connection.prepareStatement(queryBuffer.toString());
			for (int i = 0; i < idList.size(); i++) {
				if (i != 0)
					queryBuffer.append(",");
				queryBuffer.append("?");
			}

			queryBuffer.append(");");
			System.out.println(queryBuffer.toString());
			preparedStatement = connection.prepareStatement(queryBuffer.toString());
			for (int i = 0; i < idList.size(); i++) {
				System.out.println(i + 1 + " " + idList.get(i));
				preparedStatement.setInt(i + 1, idList.get(i));
			}

			preparedStatement.executeUpdate();
		}
	}
	/*
	 * public static void main(String args[]) throws ClassNotFoundException,
	 * SQLException { EmployeeDao e = new EmployeeDao(); JDBCConnection connect=new
	 * JDBCConnection(); connect.loadDriver(dbDriver); Connection
	 * connection=connect.getConnected(); }
	 */

}
