package j4.lesson10;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/lesson10/toServletJSP")

public class toServletJSP extends HttpServlet
{  
   public void doGet(HttpServletRequest request,
                     HttpServletResponse response)
            throws ServletException, IOException {

	   String name = request.getParameter("name");
       ServletContext sc = getServletContext();

       if(name.length() == 0){
           sc.getRequestDispatcher("/lesson10/error.html")
           .forward(request, response);
       }
       else {
           String language = request.getParameter("language");
    	   if (language.equalsIgnoreCase("Eng"))
               sc.getRequestDispatcher("/lesson10/VisitorEN.jsp")
               .forward(request, response);
    	   else
               sc.getRequestDispatcher("/lesson10/VisitorJP.jsp")
               .forward(request, response);
       } 
   } 
}

