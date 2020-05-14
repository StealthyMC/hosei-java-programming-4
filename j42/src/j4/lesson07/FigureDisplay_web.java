package j4.lesson07;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/lesson07/FigureDisplay_web")
public class FigureDisplay_web extends HttpServlet 
{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{		
		response.setContentType("text/html; charset=UTF-8"); 
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>FigureDisplay_web</title></head><div align='center'>");
		out.println("<body><h1>FigureDisplay</h1>"
				+ "<p><img src='" + request.getContextPath() + "/images/sakura1.jpg' width='250' height='200'/>"
				+ "<img src='" + request.getContextPath() + "/images/sakura2.jpg' width='250' height='200'/>"
				+ "<img src='" + request.getContextPath() + "/images/sakura3.png' width='250' height='200'/>"
				+ "</p><p>Please Select Figure:</p>");
		out.println("<form action='/j4/lesson07/FigureDisplay_web' method='post'>"
				+ "<p><input type='radio' name='pic' value='a'/>Sakura1"
				+ "<input type='radio' name='pic' value='b'/>Sakura2"
				+ "<input type='radio' name='pic' value='c'/>Sakura3"
				+ "<input type='submit' value='submit'/></form></p>"
				+ "</body>");
		out.println("</div></html>");
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		try 
		{
			String pic = request.getParameter("pic");
			PrintWriter writer = response.getWriter();
			writer.println("<html><body><div align='center'>");
			
			if ("a".equals(pic))
				writer.println("<h2>sakura1.jpg</h2>"
						+ "<img src='" + request.getContextPath() + "/images/sakura1.jpg'/>");
			else if ("b".equals(pic))
				writer.println("<h2>sakura2.jpg</h2>"
						+ "<img src='" + request.getContextPath() + "/images/sakura2.jpg'/>");
			else if ("c".equals(pic))
				writer.println("<h2>sakura3.png</h2>"
						+ "<img src='" + request.getContextPath() + "/images/sakura3.png'/>");
			
			writer.println("</div></body></html>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
