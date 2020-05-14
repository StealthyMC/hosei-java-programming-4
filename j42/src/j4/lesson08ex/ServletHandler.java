package j4.lesson08ex;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/lesson08ex/ServletHandler")
public class ServletHandler extends HttpServlet
{
	int usersOnline, usersTotal, usersMaxLogin;
	Map<String, String> usersList = new HashMap<>();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException
	{
		response.setContentType("text/html; charset=UTF-8"); 
		PrintWriter pw = response.getWriter();
		String action = request.getParameter("action");
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		String status = "";
		
		ServletContext sc = getServletContext();
		
		// If the user hit the register button
		if ("Register".equals(action))
		{
			// If user does not already exist in hash table, add the new user
			if (isExistingUser(user) == false)
			{
				// insert new user and password data into hash table
				usersList.put(user, pass);
				// include information written to the response object, then redirect
				status = "Registration succeeded!";
				
			}
			else if ((user == "" || pass == ""))
			{
				status = "Registration failed, input is invalid";
			}
			else
			{
				status = "Registration failed, user already exists!";
			}
			request.setAttribute("status", status);
			sc.getRequestDispatcher("/lesson08ex/SessionLogin").forward(request, response);
		}
		
		// If the user hit the login button
		if ("Login".equals(action))
		{
			// If user doesn't exist, or if password is incorrect, don't login the user
			if ((usersList.containsKey(user) == false) ||
			(usersList.get(user).equals(pass) == false))
			{
				status = "Login failure!";
				request.setAttribute("status", status);
				sc.getRequestDispatcher("/lesson08ex/SessionLogin").forward(request, response);
			}
			else	// otherwise, if the login is a success...
			{
				usersOnline++;	// increase user count
				usersTotal = (usersList.keySet()).size();	// update number of total users in database
				
				// set attributes
				request.setAttribute("user", user);
				request.setAttribute("online_users", usersOnline);
				request.setAttribute("total_users", usersTotal);
				
				sc.getRequestDispatcher("/lesson08ex/SessionUserInfo").forward(request, response);
			}
		}
		
		// If user logged out
		if ("Logout".equals(action))
		{
			// Decrease amount of online users, send user back to login page
			usersOnline--;
			sc.getRequestDispatcher("/lesson08ex/SessionLogin").forward(request, response);
		}
		
	}
	
	// Checks if [user] exists in current hash table of users
	public Boolean isExistingUser(String user)
	{
		Boolean status = false;
		Set<String> usersSet = usersList.keySet();
		status = usersSet.contains(user);
		
		return status;
	}
}
