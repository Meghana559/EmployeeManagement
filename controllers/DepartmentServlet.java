package com.planon.mvc.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.planon.mvc.dao.Departmentdao;
import com.planon.mvc.model.Department;
import com.planon.utils.CommonUtils;

@WebServlet({ "/dept", "/deptnew", "/deptedit", "/deptinsert", "/deptdelete", "/deptupdate", "/deptlist", "/deletion",
		"/deptquery", "/comparisions" })
public class DepartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Departmentdao departmentDao;

	public DepartmentServlet() {
		super();
		this.departmentDao = new Departmentdao();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();
		switch (action) {
		case "/deptnew":
			showNewForm(request, response);
			break;
		case "/deptedit":
			showEditForm(request, response);
			break;
		case "/deptinsert":
			insertDepartment(request, response);
			break;
		case "/deptdelete":
			deleteDepartment(request, response);
			break;
		case "/deptupdate":
			try {
				updateDepartment(request, response);
			} catch (ClassNotFoundException | ServletException | IOException | SQLException e) {
				response.sendRedirect("Error.jsp");
			}
			break;
		case "/deptlist":
			listDepartment(request, response);
			break;
		case "/deptquery":
			searchDepartments(request, response);
			break;
		case "/comparisions":
			compare(request, response);
		}
	}

	private void compare(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int column = Integer.parseInt(request.getParameter("id"));

		List<Department> listDepartment = null;
		List<Department> sortedList = null;
		try {
			listDepartment = new Departmentdao().selectAllDepartments();
			sortedList = new Departmentdao().sortColumns(listDepartment, column);
			request.setAttribute("listDepartment", sortedList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Department-list.jsp");
			dispatcher.forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			CommonUtils.errorRedirection(request, response);
		}

	}

	private void searchDepartments(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Department> listdepartment = null;
		try {
			listdepartment = departmentDao.searchAllDepartments(request.getParameter("data"));
			request.setAttribute("listDepartment", listdepartment);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Department-list.jsp");
			dispatcher.forward(request, response);
		} catch (ClassNotFoundException | SQLException exception) {
			CommonUtils.errorRedirection(request, response);
		}

	}

	private void listDepartment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Department> listdepartment = null;
		try {
			listdepartment = departmentDao.selectAllDepartments();
			request.setAttribute("listDepartment", listdepartment);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Department-list.jsp");
			dispatcher.forward(request, response);
		} catch (ClassNotFoundException | SQLException exception) {
			CommonUtils.errorRedirection(request, response);
		}

	}

	private void updateDepartment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		Integer deptid = Integer.parseInt(request.getParameter("id"));
		System.out.println(deptid);
		String deptName = request.getParameter("dname");
		String deptCode = request.getParameter("dcode");
		Department dept2 = new Department(deptid, deptName, deptCode);
		try {
			departmentDao.updateDepartment(dept2);
			response.sendRedirect("deptlist");
		} catch (ClassNotFoundException | SQLException exception) {
			CommonUtils.errorRedirection(request, response);
		}

	}

	private void deleteDepartment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			departmentDao.deleteDepartment(id);
			response.sendRedirect("deptlist");
		} catch (ClassNotFoundException | SQLException exception) {
			CommonUtils.errorRedirection(request, response);
		}

	}

	private void insertDepartment(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String deptName = request.getParameter("dname");
		String deptCode = request.getParameter("dcode");
		Department dept = new Department(deptName, deptCode);
		try {
			departmentDao.insertDepartment(dept);
			response.sendRedirect("deptlist");
		} catch (ClassNotFoundException | SQLException exception) {
			CommonUtils.errorRedirection(request, response);
		}

	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Department existingDepartment = null;
		List<Department> listDepartment = null;
		try {
			existingDepartment = departmentDao.selectDepartmentById(id);
			listDepartment = new Departmentdao().selectAllDepartments();
			request.setAttribute("listDepartment", listDepartment);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Department-form.jsp");
			request.setAttribute("existingdepartment", existingDepartment);
			dispatcher.forward(request, response);
		} catch (ClassNotFoundException | SQLException exception) {
			CommonUtils.errorRedirection(request, response);
		}

	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("Department-form.jsp");
		dispatcher.forward(request, response);

	}

}
