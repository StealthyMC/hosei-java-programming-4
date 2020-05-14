package j4.lesson07;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/lesson07/TomcatTest")
public class TomcatTest extends HttpServlet {
	public void doGet(HttpServletRequest request,
	                  HttpServletResponse response)
	           throws ServletException, IOException {
	   PrintWriter out = response.getWriter();
	   out.println("<html><head><title>TomcatTest</title></head>");
	   out.println("<body>");
	   out.println("<h2>Success, congratulations!</h2></body></html>");
	   out.println("</body></html>");
	}
}
