package com.planon.mvc.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.planon.mvc.dao.Departmentdao;
import com.planon.mvc.dao.EmployeeDao;
import com.planon.mvc.model.Department;
import com.planon.mvc.model.Employee;
import com.planon.utils.CommonUtils;

@WebServlet({ "/emp", "/empnew", "/empedit", "/empinsert", "/empupdate", "/empdelete", "/emplist", "/empquery",
		"/empQueries", "/empcomparisions", "/empMultiple" })
public class EmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private EmployeeDao employeeDao;
	@SuppressWarnings("unused")
	private Departmentdao departmentDao;

	public EmpServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		switch (action) {

		case "/empnew":
			showNewForm(request, response);
			break;
		case "/empedit":
			showEditForm(request, response);
			break;
		case "/empinsert":
			try {
				insertEmployee(request, response);
			} catch (ClassNotFoundException | ServletException | IOException | SQLException e) {
				response.sendRedirect("Error.jsp");
			}
			break;
		case "/empdelete":
			deleteEmployee(request, response);
			break;
		case "/empupdate":
			try {
				updateEmployee(request, response);
			} catch (ClassNotFoundException | ServletException | IOException | SQLException e) {
				response.sendRedirect("Error.jsp");
			}
			break;
		case "/empquery":
			searchEmployee(request, response);
			break;
		case "/emplist":
			listEmployee(request, response);
			break;
		case "/empQueries":
			employeeSQLQueries(request, response);
			break;
		case "/empcomparisions":
			compare(request, response);
			break;
		case "/empMultiple":
			try {
				deleteMultipleEmployees(request, response);
			} catch (ClassNotFoundException | ServletException | IOException | SQLException exception) {
				response.sendRedirect("Error.jsp");
			}
			break;
		}
	}

	private void deleteMultipleEmployees(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		List<Employee> listEmployee = null;
		ArrayList<Integer> idarrayList = new ArrayList<Integer>();
		String[] employeeID = request.getParameterValues("deleteMultipleEmp");
		if (employeeID == null) {
			request.setAttribute("ShowErrorMessage", "No Records Selected! Please select atleast one record");
			CommonUtils.errorRedirection(request, response);
		}
		for (int i = 0; i < employeeID.length; i++) {
			int empId = Integer.valueOf(employeeID[i]);
			idarrayList.add(empId);
		}
		try {
			new EmployeeDao().deleteMultiple(idarrayList);
			listEmployee = new EmployeeDao().selectAllEmployee();
			request.setAttribute("listEmployee", listEmployee);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Employee-list.jsp");
			dispatcher.forward(request, response);

		} catch (ClassNotFoundException | SQLException e) {
			CommonUtils.errorRedirection(request, response);
		}

	}

	private void compare(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int column = Integer.parseInt(request.getParameter("id"));

		List<Employee> listEmployee = null;
		List<Employee> sortedList = null;
		try {
			listEmployee = new EmployeeDao().selectAllEmployee();
			sortedList = new EmployeeDao().sortColumns(listEmployee, column);
			request.setAttribute("listEmployee", sortedList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Employee-list.jsp");
			dispatcher.forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			CommonUtils.errorRedirection(request, response);
		}
	}

	private void employeeSQLQueries(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Hii");
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println(id);
		List<Employee> listEmployee = null;

		try {
			listEmployee = new EmployeeDao().employeeSQLQueries(id);
			request.setAttribute("listEmployee", listEmployee);
			RequestDispatcher dispatcher = request.getRequestDispatcher("EmployeeSalaryQueryList.jsp");
			dispatcher.forward(request, response);

		} catch (ClassNotFoundException | SQLException e) {
			CommonUtils.errorRedirection(request, response);
		}

	}

	private void searchEmployee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Employee> listEmployee = null;
		List<Employee> highpaidList = null;
		List<Employee> minpaidList = null;
		List<Employee> maxpaidListforeachDept = null;
		List<Employee> minpaidListforeachDept = null;
		try {
			listEmployee = new EmployeeDao().searchAllEmployee(request.getParameter("data"));
			highpaidList = new EmployeeDao().maxSalary(listEmployee);
			minpaidList = new EmployeeDao().minSalary(listEmployee);
			maxpaidListforeachDept = new EmployeeDao().maxSalaryForEachDepartment(listEmployee);
			minpaidListforeachDept = new EmployeeDao().minSalaryForEachDepartment(listEmployee);
			request.setAttribute("listEmployee", listEmployee);
			request.setAttribute("highestPaidList", highpaidList);
			request.setAttribute("minPaidList", minpaidList);
			request.setAttribute("maxpaidListforeachDept", maxpaidListforeachDept);
			request.setAttribute("minpaidListforeachDept", minpaidListforeachDept);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Employee-list.jsp");
			dispatcher.forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			CommonUtils.errorRedirection(request, response);
		}

	}

	private void listEmployee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Employee> listEmployee = null;
		List<Employee> highpaidList = null;
		List<Employee> minpaidList = null;
		List<Employee> maxpaidListforeachDept = null;
		List<Employee> minpaidListforeachDept = null;
		try {
			listEmployee = new EmployeeDao().selectAllEmployee();
			highpaidList = new EmployeeDao().maxSalary(listEmployee);
			minpaidList = new EmployeeDao().minSalary(listEmployee);
			maxpaidListforeachDept = new EmployeeDao().maxSalaryForEachDepartment(listEmployee);
			minpaidListforeachDept = new EmployeeDao().minSalaryForEachDepartment(listEmployee);
			request.setAttribute("listEmployee", listEmployee);
			request.setAttribute("highestPaidList", highpaidList);
			request.setAttribute("minPaidList", minpaidList);
			request.setAttribute("maxpaidListforeachDept", maxpaidListforeachDept);
			request.setAttribute("minpaidListforeachDept", minpaidListforeachDept);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Employee-list.jsp");
			dispatcher.forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			CommonUtils.errorRedirection(request, response);
		}

	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Department> listDepartment = null;
		try {
			listDepartment = new Departmentdao().selectAllDepartments();
			request.setAttribute("listDepartment", listDepartment);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Employee-form.jsp");
			dispatcher.forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			CommonUtils.errorRedirection(request, response);
		}

	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Department> listDepartment = null;
		int id = Integer.parseInt(request.getParameter("id"));
		Employee existingEmployee = null;
		try {
			System.out.println("entered");
			existingEmployee = new EmployeeDao().selectEmployeeById(id);
			listDepartment = new Departmentdao().selectAllDepartments();
			request.setAttribute("listDepartment", listDepartment);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Employee-form.jsp");
			request.setAttribute("employee", existingEmployee);
			dispatcher.forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			CommonUtils.errorRedirection(request, response);
		}

	}

	private void deleteEmployee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			new EmployeeDao().deleteEmployee(id);
			response.sendRedirect("emplist");
		} catch (ClassNotFoundException | SQLException e) {
			CommonUtils.errorRedirection(request, response);
		}

	}

	private void insertEmployee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		int deptid = Integer.parseInt(request.getParameter("dcode"));
		Department dept = new Departmentdao().selectDepartmentById(deptid);
		String deptCode = dept.getDepartmentCode();
		String deptName = dept.getDepartmentName();
		String firstName = request.getParameter("fname");
		String lastName = request.getParameter("lname");
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
		String dob = request.getParameter("dob");
		String doj = request.getParameter("doj");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dateb = null;
		java.util.Date datej = null;
		try {
			dateb = formatter.parse(dob);
			datej = formatter.parse(doj);
		} catch (ParseException exception) {
			response.sendRedirect("Error.jsp");
		}
		Double salary = Double.parseDouble(request.getParameter("salary"));
		Department dept2 = new Department(deptid, deptCode, deptName);
		Employee emp = new Employee(id, dept2, firstName, lastName, email, gender, salary, dateb, datej);
		try {
			new EmployeeDao().insertEmployee(emp);
			response.sendRedirect("emplist");
		} catch (SQLException | ClassNotFoundException e) {
			CommonUtils.errorRedirection(request, response);
		}
	}

	private void updateEmployee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		int deptid = Integer.parseInt(request.getParameter("dcode"));
		Department dept = new Departmentdao().selectDepartmentById(deptid);
		String deptCode = dept.getDepartmentCode();
		String deptName = dept.getDepartmentName();
		String firstName = request.getParameter("fname");
		String lastName = request.getParameter("lname");
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
		String dob = request.getParameter("dob");
		String doj = request.getParameter("doj");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dateb = null;
		java.util.Date datej = null;
		try {
			dateb = formatter.parse(dob);
			datej = formatter.parse(doj);
		} catch (ParseException exception) {
			response.sendRedirect("Error.jsp");
		}
		// CommonUtils.parseDate(dob, doj, request, response);
		Double salary = Double.parseDouble(request.getParameter("salary"));
		Department dept2 = new Department(deptid, deptCode, deptName);
		Employee emp = new Employee(id, dept2, firstName, lastName, email, gender, salary, dateb, datej);
		try {
			new EmployeeDao().updateEmployee(emp);
			response.sendRedirect("emplist");
		} catch (SQLException | ClassNotFoundException e) {
			CommonUtils.errorRedirection(request, response);
		}

	}
	/*
	 * public static void main(String args[]) { EmpServlet eservlet=new
	 * EmpServlet(); JDBCConnection connect=new JDBCConnection();
	 * connect.loadDriver("com.mysql.cj.jdbc.Driver"); Connection
	 * connection=connect.getConnected();
	 * 
	 * 
	 * }
	 */

}
