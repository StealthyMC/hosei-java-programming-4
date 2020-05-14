package j4.lesson10;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/lesson10/toServlet")

public class toServlet extends HttpServlet
{  
   int countE = 1, countJ = 1;
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
           response.setContentType("text/html; charset=UTF-8");
           String language = request.getParameter("language");
    	   String replyMsg, number;
    	   if (language.equalsIgnoreCase("Eng")){
    		   replyMsg = "Welcome " + name + "!";
    		   number = "Your Number: E-" + countE++;
    	   }
    	   else {
    		   replyMsg = "ようこそ, " + name + "!";
    		   number = "あなたの番号: J-" + countJ++;
    	   }
    	   PrintWriter out = response.getWriter();
           out.println(
        	   "<!DOCTYPE html><html>\n" +
               "<head><title>Welcome</title></head>\n" +
               "<body><center>\n" +
               "<h3>\n" +  replyMsg + "</h3>\n" +
               "<h3>\n" +  number + "</h3>\n" +
               "</center></body>\n" +
               "</html>\n");
       }
   } 
}
