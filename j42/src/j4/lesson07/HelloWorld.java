package j4.lesson07;

import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/lesson07/HelloWorld")
public class HelloWorld extends HttpServlet {
	   public void doGet(HttpServletRequest request,
	                                 HttpServletResponse response)
	       throws ServletException, IOException {
	   PrintWriter out = response.getWriter();
	   out.println("<html><head><title>Hello</title></head>");
	   out.println("<body>");
	   out.println("<h2>" + new java.util.Date() + "</h2>");
	   out.println("<h1>Hello World</h1></body></html>");
	   }
	}

