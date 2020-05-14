package j4.lesson07;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/lesson07/Register_web")
public class Register_web extends HttpServlet
{
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException
	{
		response.setContentType("text/html; charset=UTF-8"); 
		PrintWriter pw = response.getWriter();
		
		pw.println("<html><head><title>FigureDisplay_web</title></head><div align='center'><body>");
		pw.println("<form action='/j4/lesson07/Register_web' method='post'>");
		pw.println("<table style='width:40%' border='1'>"
				+ "<tr> <th></th> <th><h1>Register</h1></th> </tr>"
				+ "<tr> <th><p>Name:</p></th> <th><input type='text' name='name'/></th> </tr>"
				+ "<tr> <th><p>Password:</p></th> <th><input type='password' name='password'/></th> </tr>"
				+ "<tr> <th><p>Confirm:</p></th> <th><input type='password' name='password_confirm'/></th> </tr>"
				+ "<tr> <th><p>Email:</p></th> <th><input type='text' name='email'/></th> </tr>"
				+ "<tr> <th><p>Tel:</p></th> <th><input type='text' name='name'/></th> </tr>"
				+ "<tr> <th><p>Gender</p></th> <th>"
				+ "<input type='radio' name='gender' value='male'/>Male"
				+ "<input type='radio' name='gender' value='female'/>Female"
				+ "</th> </tr>"
				+ "<tr> <th><p>Hobby:</p></th> <th>"
				+ "<input type='checkbox' name='hobby' value='sports'/>Sports"
				+ "<input type='checkbox' name='hobby' value='music'/>Music"
				+ "<input type='checkbox' name='hobby' value='game'/>Game"
				+ "</th> </tr>"
				+ "<tr> <th></th> <th><input type='reset' value='reset'/> "
				+ "<input type='submit' value='submit'/></th> </tr>");
		pw.println("</table></form>");
		pw.println("</body></div></html>");
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException
	{
		PrintWriter writer = response.getWriter();
		writer.println("<html><body><div align='center'>");
		writer.println("<h1>Register Result</h1>");
		writer.println("</div></body></html>");
	}
}
