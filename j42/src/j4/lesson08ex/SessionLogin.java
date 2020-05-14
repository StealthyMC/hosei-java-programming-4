package j4.lesson08ex;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/lesson08ex/SessionLogin")
public class SessionLogin extends HttpServlet 
{
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException
	{
		response.setContentType("text/html; charset=UTF-8"); 
		PrintWriter pw = response.getWriter();
		
		String status = "";
		if (request.getAttribute("status") != null)
			status = (String) request.getAttribute("status");
		
		pw.println("<html><head><title>FigureDisplay_web</title></head><div align='center'>");
		pw.println("<h2>Login</h2><p>Please enter your username and password</p>");
		
		pw.println("<form action='/j4/lesson08ex/ServletHandler' method='get'>");
		
		pw.println("<table style=40%");
		pw.println("<tr> <td><p>Username:</p></td> <td><input type='text' name='user'/></td> </tr>");
		pw.println("<tr> <td><p>Password:</p></td> <td><input type='password' name='pass'/></td> </tr>");
		pw.println("</table>");
		pw.println("<p><input type='submit' name='action' value='Login'/>"
				+ "<input type='submit' name='action' value='Register'/></p>");
		
		pw.println("</form>");
		
		pw.println(status);
	}
}
