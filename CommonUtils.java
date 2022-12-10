package com.planon.utils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommonUtils {
	public static void errorRedirection(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		errorRedirection(request, response);
	}

	/*public static void parseDate(String dob, String doj, HttpServletRequest request, HttpServletResponse response) throws IOException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dateb = formatter.parse(dob);
			Date datej = formatter.parse(doj);
		} catch (ParseException exception) {
			response.sendRedirect("Error.jsp");
		}
	}*/
}
