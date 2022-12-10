package com.planon.mvc.model;

public class Department {
	private String departmentCode, departmentName;
	private int departmentid;

	public Department(int departmentID, String departmentName, String departmentCode) {
		super();
		this.departmentid = departmentID;
		this.departmentName = departmentName;
		this.departmentCode = departmentCode;
	}

	public Department(String departmentName, String departmentCode) {
		super();
		this.departmentName = departmentName;
		this.departmentCode = departmentCode;
	}

	public int getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(int departmentid) {
		this.departmentid = departmentid;
	}

	@Override
	public String toString() {
		return "Department [departmentCode=" + departmentCode + ", departmentName=" + departmentName + ", departmentid="
				+ departmentid + "]";
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

}
