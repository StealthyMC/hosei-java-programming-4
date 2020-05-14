package j4.lesson07;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/lesson07/HelloUser")
public class HelloUser extends HttpServlet {
	   int count = 1;
	   public void doGet(HttpServletRequest request,
	                                 HttpServletResponse response)
	       throws ServletException, IOException {
	   String host = request.getRemoteHost();
	   String ip = request.getRemoteAddr();
	   String user = request.getRemoteUser();
	   int port = request.getRemotePort();
	   System.out.print("Access-" + count++ + " IP/Port " + ip +"/" + port + "\n");
	   PrintWriter out = response.getWriter();
	   out.println("<html><head><title>Hello</title></head>");
	   out.println("<body>");
	   out.println("<h3> Hello! Your Order: " + count + "</h3>");
	   out.println("<h3> Your IP: " + ip + "</h3>");
	   out.println("<h3> Your Port: " + port + "</h3>");
	   out.println("</body></html>");
	   }
	}

