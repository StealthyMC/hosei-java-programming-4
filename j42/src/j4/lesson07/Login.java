package j4.lesson07;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/lesson07/Login")

public class Login extends HttpServlet {
  public void doPost(HttpServletRequest request, HttpServletResponse response) {
    // Get the parameter from the request
    String username = request.getParameter("username");
    // Send the response back to the user
    try { 
      response.setContentType("text/html");
      PrintWriter writer = response.getWriter();
      writer.println("<html><body>");
      writer.println("Thank you, " + username + ". You are now logged into the system.");
      writer.println("</body></html>");
      writer.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

