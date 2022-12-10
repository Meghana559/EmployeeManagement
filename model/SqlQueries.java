package com.planon.mvc.model;

public class SqlQueries {
	public static final String INSERT_EMPLOYEE = "Insert into employeetable(EmployeeId,departmentid,FirstName,LastName,email,Gender,DateOfBirth,DateOfJoining,Salary) values(?,?,?,?,?,?,?,?,?)";

	public static final String SELECT_EMPLOYEE_BY_ID = "SELECT e.employeeid,e.departmentid,d.departmentcode,d.departmentname,e.firstname,e.lastname,e.email,e.gender,e.dateofbirth,e.dateofJoining,e.salary FROM employeetable e INNER JOIN departmenttable d ON  e.departmentid = d.departmentid and e.employeeid=?;";

	public static final String SELECT_ALL_EMPLOYEES = "SELECT e.employeeid,e.departmentid,d.departmentcode,d.departmentname,e.firstname,e.lastname,e.email,e.gender,e.dateofbirth,e.dateofJoining,e.salary\r\n"
			+ "FROM employeetable e\r\n" + "INNER JOIN departmenttable d\r\n" + "ON  e.departmentid = d.departmentid;";

	public static final String DELETE_EMPLOYEE_BY_ID = "delete from employeetable where EmployeeId=?;";

	public static final String UPDATE_EMPLOYEE = "update employeetable set departmentid=?, FirstName=?,LastName=?,email=?,Gender=?,DateOfBirth=?,DateOfJoining=?,Salary=? where EmployeeId=?;";

	public static final String MAXIMUM_SALARIED_EMPLOYEES = "SELECT e.employeeid,e.departmentid,d.departmentcode,d.departmentname,e.firstname,e.lastname,e.email,e.gender,e.dateofbirth,e.dateofJoining,e.salary\r\n"
			+ "FROM employeetable e,  departmenttable d\r\n" + "WHERE e.salary =  \r\n" + "(SELECT MAX(salary) \r\n"
			+ "FROM employeetable\r\n" + "WHERE e.departmentid=d.departmentid );";

	public static final String MINIMUM_SALARIED_EMPLOYEES = "SELECT e.employeeid,e.departmentid,d.departmentcode,d.departmentname,e.firstname,e.lastname,e.email,e.gender,e.dateofbirth,e.dateofJoining,e.salary\r\n"
			+ "FROM employeetable e,  departmenttable d\r\n" + "WHERE e.salary =  \r\n" + "(SELECT MIN(salary) \r\n"
			+ "FROM employeetable\r\n" + "WHERE e.departmentid=d.departmentid );";

	public static final String MAXIMUM_SALARIED_EMPLOYEES_FROM_EACH_DEPARTMENT = "SELECT e.employeeid,e.departmentid,d.departmentcode,d.departmentname,e.firstname,e.lastname,e.email,e.gender,e.dateofbirth,e.dateofJoining,e.salary\r\n"
			+ "FROM employeetable e\r\n" + "INNER JOIN departmenttable d\r\n"
			+ "ON  e.departmentid = d.departmentid\r\n"
			+ "INNER JOIN (SELECT departmentid, MAX(salary) AS max_salary\r\n" + " FROM  employeetable\r\n"
			+ "            GROUP BY departmentid) d1 ON e.departmentid = d1.departmentid\r\n"
			+ "                               AND e.salary = d1.max_salary;";

	public static final String MINIMUM_SALARIED_EMPLOYEES_FROM_EACH_DEPARTMENT = "SELECT e.employeeid,e.departmentid,d.departmentcode,d.departmentname,e.firstname,e.lastname,e.email,e.gender,e.dateofbirth,e.dateofJoining,e.salary\r\n"
			+ "FROM employeetable e\r\n" + "INNER JOIN departmenttable d\r\n"
			+ "ON  e.departmentid = d.departmentid\r\n"
			+ "INNER JOIN (SELECT departmentid, MIN(salary) AS min_salary\r\n" + "FROM  employeetable\r\n"
			+ "            GROUP BY departmentid) d1 ON e.departmentid = d1.departmentid\r\n"
			+ "                               AND e.salary = d1.min_salary;";

	public static final String INSERT_DEPARTMENT = "insert into departmenttable(departmentName,departmentCode) values(?,?);";

	public static final String SELECT_DEPARTMENT_BY_ID = "select * from departmenttable where departmentid=?;";

	public static final String SELECT_ALL_DEPARTMENTS = "select *from departmenttable;";

	public static final String DELETE_DEPARTMENT_BY_ID = "delete from departmenttable where departmentid=?;";

	public static final String UPDATE_DEPARTMENT = "update departmenttable set departmentCode=?,departmentName=? where departmentid=?";
}
