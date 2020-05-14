package j4.lesson10ex_16A2467;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/lesson10ex/Process")
public class Process extends HttpServlet
{
	// create map of users
	// p1:	user object		p2:	password
	Map<User, String> usersList = new HashMap<>();
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException
	{
		response.setContentType("text/html; charset=UTF-8"); 
		PrintWriter pw = response.getWriter();
		ServletContext sc = getServletContext();
		
		// fetch user information
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		String gender = request.getParameter("gender");
		String color = request.getParameter("color");
		
		String[] car = request.getParameterValues("car");
		ArrayList<String> carlist = new ArrayList<>();
		// get the values of selected cars
		if (car != null)
		{
			for (int i = 0; i < car.length; i++)
				carlist.add(car[i]);
		}
		
		// Handle login
		if (request.getParameter("action").equalsIgnoreCase("Log In"))
		{
			if (user.length() == 0 || pass.length() == 0)
			{
				request.setAttribute("status", "Please input a user name and password!");
				sc.getRequestDispatcher("/lesson10ex_jsp/Login.jsp").forward(request, response);
			}
			/*else if ((findUser(user).getUsername() != null) && ()))
			{
				
			}*/
			else
			{
				// Information to use when info is sent to JSP
				User userFinal = new User();
				
				// if user already exists, update user info and redirect to User.jsp
				if (findUser(user).getUsername() != null)
				{
					userFinal = updateUser(findUser(user), user, pass, gender, color, carlist);
				}
				// Otherwise, if the user doesn't exist, make a new one and then redirect
				else
				{
					// Create new user
					User newUser = createUser(user, pass, gender, color, carlist);
					userFinal = newUser;
				}
				
				// Information that User.jsp needs, all parameters except pass lol
				request.getSession().setAttribute("username", userFinal.getUsername());
				request.getSession().setAttribute("gender", userFinal.getGender());
				request.getSession().setAttribute("color", userFinal.getColor());
				request.getSession().setAttribute("minicar", userFinal.getMiniCar());
				request.getSession().setAttribute("sedan", userFinal.getSedan());
				request.getSession().setAttribute("suv", userFinal.getSUV());
				request.getSession().setAttribute("money", userFinal.getMoney());
				// Redirect user to car page
				sc.getRequestDispatcher("/lesson10ex_jsp/User.jsp").forward(request, response);	
			}
		}
		// Otherwise handle logout
		else if (request.getParameter("action").equalsIgnoreCase("Logout"))
		{
			// Log the user out and redirect to login
			request.getSession().invalidate();
			sc.getRequestDispatcher("/lesson10ex_jsp/Login.jsp").forward(request, response);
		}
	}
	
	// Checks if [user] exists in current hash table of users
	public User findUser(String user)
	{
		User userEntry = new User();
		
		// Find out whether or not there is an existing user
		for (Map.Entry<User, String> entry : usersList.entrySet()) 
		{
			// If user is found
			if (user.equalsIgnoreCase(entry.getKey().getUsername()))
			{
				userEntry = entry.getKey();
			}
		}
		// Return reference to user object
		return userEntry;
	}
	
	public User createUser(String username, String pass, String gender, String color, 
			ArrayList<String> carlist)
	{
		// Create new user and set variables
		User newUser = new User();
		newUser.setUsername(username);
		newUser.setGender(gender);
		newUser.setColor(color);
		newUser.setCarlist(carlist);
		
		// Set car properties
		for (String i: newUser.getCarlist())
		{
			if ("minicar".equalsIgnoreCase(i))
				newUser.setMiniCar(true);
			if ("sedan".equalsIgnoreCase(i))
				newUser.setSedan(true);
			if ("suv".equalsIgnoreCase(i))
				newUser.setSUV(true);
		}
		
		// Insert new user into map
		usersList.put(newUser, pass);
		
		// Return new user object
		return newUser;
	}
	public User updateUser(User user, String username, String pass, String gender, String color, 
			ArrayList<String> carlist)
	{
		user.setGender(gender);
		user.setColor(color);
		user.setCarlist(carlist);
		
		// Adjust car properties
		for (String i: user.getCarlist())
		{
			if ("minicar".equalsIgnoreCase(i))
				user.setMiniCar(true);
			else
				user.setMiniCar(false);
			if ("sedan".equalsIgnoreCase(i))
				user.setSedan(true);
			else
				user.setSedan(false);
			if ("suv".equalsIgnoreCase(i))
				user.setSUV(true);
			else
				user.setSUV(false);
		}
		
		return user;
	}
}
