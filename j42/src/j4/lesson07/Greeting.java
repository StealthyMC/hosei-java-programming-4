package j4.lesson07;

import java.util.*;
import java.util.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/lesson07/Greeting")
public class Greeting extends HttpServlet
{
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		
		int hour = date.getHours();
		
		String greeting;
		if (hour > 5 && hour < 12)
			greeting = "Good Morning";
		else if (hour >= 12 && hour < 18)
			greeting = "Good Afternoon";
		else
			greeting = "Good Evening";
		
		
		out.println("<html>");
		out.println("<head><div align='center'>" 
				+ "<img src='" + request.getContextPath() + "/images/car.jpg'/>"
				+ "<p>Current Time " + dateFormat.format(date) + "</p>"
				+ "<h3>" + greeting + "</h3></div>" + "</head>");
		out.println("</html>");
	}
}
