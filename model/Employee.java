package com.planon.mvc.model;

import java.util.Date;

public class Employee {
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	private int employeeId, departmentid;
	private String firstName, lastName, email, gender;
	private Double salary;
	private Date dateOfBirth, dateOfJoining;
	private Department department;

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Employee(int employeeId, Department dept, String firstName, String lastName, String email, String gender,
			double salary, Date dateOfBirth, Date dateOfJoining) {
		super();
		this.employeeId = employeeId;
		this.department = dept;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.salary = salary;
		this.dateOfBirth = dateOfBirth;
		this.dateOfJoining = dateOfJoining;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Date getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public int getDepartmentid() {
		return getDepartment().getDepartmentid();
	}

	public void setDepartmentid(int departmentid) {
		this.departmentid = departmentid;
	}

	@Override
	public String toString() {
		return "Employee [EmployeeId=" + employeeId + ", departmentid=" + departmentid + ", FirstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", Gender=" + gender + ", salary=" + salary
				+ ", DateOfBirth=" + dateOfBirth + ", dateOfJoining=" + dateOfJoining + ", department=" + department
				+ "]";
	}

}
