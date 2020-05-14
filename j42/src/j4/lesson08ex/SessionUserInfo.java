package j4.lesson08ex;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/lesson08ex/SessionUserInfo")
public class SessionUserInfo extends HttpServlet
{
	int loginCount = 0; 
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException
	{
		HttpSession hs = request.getSession(true);
		String user = (String) request.getAttribute("user");
		
		String welcomeMsg = "";
		
		// Check if session is new or not
		if (hs.getAttribute("count") == null)
		{
			hs.setAttribute("count", 1); 	// set count to 1 for initial session
			welcomeMsg = " first time ";
		}
		else
		{
			int cn = (int) hs.getAttribute("count");
			cn++;
			welcomeMsg = cn + " time ";
			hs.setAttribute("count", cn);
			hs.setMaxInactiveInterval(5);
		}
		
		long createTime = hs.getCreationTime();
	    Date createDate = new Date(createTime);
	    int sessionInterval = hs.getMaxInactiveInterval();
	    int totalUsers = (int) request.getAttribute("total_users");
	    int onlineUsers = (int) request.getAttribute("online_users");
		
		response.setContentType("text/html; charset=UTF-8"); 
		PrintWriter pw = response.getWriter();
		
		pw.println("<html><head><title>SessionUserInfo</title></head><div align='center'>");
		
		pw.println("<hr><h2>Dear " + user + "</h2><p></p>"
				+ "<p>Your Session ID: " + hs.getId() + "</p>"
				+ "<p>Your session was created at " + createDate + "</p>"
				+ "<p><b>Session Interval: " + sessionInterval + " seconds</b></p>"
				+ "<p>Welcome to the " + welcomeMsg + "login!</p>"
				+ "<p>The maximum login user is " + "[user]" + "</p>"
				+ "<p>Total number of users: " + totalUsers + "</p>"
				+ "<p>Number of online users: " + onlineUsers + "</p><hr>");
		pw.println("<form action='/j4/lesson08ex/ServletHandler' method='get'>");
		pw.println("<p><input type='submit' name='action' value='Logout'/></p>");
		pw.println("</form>");
		
		pw.println("</body></div></html>");
	}
}
