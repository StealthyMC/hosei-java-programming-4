package j4.lesson08;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/lesson08/Servlet2Servlet")

public class Servlet2Servlet extends HttpServlet
{
   public void doGet(HttpServletRequest request,
                     HttpServletResponse response)
   throws ServletException, IOException
   {
         //フォームデータの取得
         String doWhat = request.getParameter("what");

         //サーブレットコンテキストの取得
         ServletContext sc = getServletContext();

         //リクエストの転送
         if(doWhat.length() == 0){
        	 String url = "http://jianhua.cis.k.hosei.ac.jp/";
        	 response.sendRedirect(url);
         }
         else if(doWhat.equalsIgnoreCase("SessionInfo")) {
             sc.getRequestDispatcher("/lesson08/SessionInfo")
             .forward(request, response);
         }
         else if(doWhat.equalsIgnoreCase("HelloWorld")) {
             sc.getRequestDispatcher("/lesson07/HelloWorld")
             .forward(request, response);
         }
         else {
        	    response.setContentType("text/html; charset=UTF-8");
        	    PrintWriter pw = response.getWriter();
        	    pw.println( "<!DOCTYPE html><html>" +
        	       "<head><title>ServletForward</title></head>\n" +
        	       "<body><center>\n");
        	    pw.println(
        	       "<p></p>ServletIncluded will do the following<hr>" );
        	    //インクルードファイル内で処理されています
        	    String disp = "/lesson08/ServletIncluded";
        	    RequestDispatcher dispatch =
        	       request.getRequestDispatcher(disp);
        	    dispatch.include(request, response);
        	    pw.println("<p></p>" +
        	    		   "ServletIncluded did the above");
        	    pw.println(
        	       "</center></body>\n" +
        	       "</html>\n");
        	    }
   }
}
