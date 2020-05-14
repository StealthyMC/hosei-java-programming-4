package j4.lesson08;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/lesson08/Servlet2HTML")

public class Servlet2HTML extends HttpServlet
{
   public void doGet(HttpServletRequest request,
                     HttpServletResponse response)
            throws ServletException, IOException {
	   //フォームデータの取得
	   String carname = request.getParameter("goods");
	   //サーブレットコンテキストの取得
       ServletContext sc = getServletContext();
       //リクエストの転送
       if(carname.length() != 0){
          sc.getRequestDispatcher("/lesson08/thanks.html")
              .forward(request, response);
       }
       else {
          sc.getRequestDispatcher("/lesson08/error.html")
              .forward(request, response);
       }
   } 
}
