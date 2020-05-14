package j4.lesson08;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/lesson08/ServletIncluded")

public class ServletIncluded extends HttpServlet {
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{
	response.setContentType("text/html; charset=UTF-8");
	String doWhat = request.getParameter("what");
    PrintWriter out = response.getWriter();
    out.println("<p style=\"background:#ffff00\">");
    out.println(
    	"<b>" + doWhat + "</b> does not exist!<br>\n</p>" +
        "Input either SessionInfo or HelloWorld<hr>");
  }
}
	
	
