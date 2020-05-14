package j4.lesson07;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/lesson07/SelectCar")


public class SelectCar extends HttpServlet
{
   public void doGet(HttpServletRequest request,
                     HttpServletResponse response)
   throws ServletException
   {
      try{
         //フォームデータの取得
         String carname = request.getParameter("car");
         String carcolor = request.getParameter("color");

         //コンテンツタイプの設定
         response.setContentType("text/html; charset=UTF-8");

         //HTML文書の書き出し
         PrintWriter pw = response.getWriter();
         if(carname.length() != 0){
            pw.println("<!DOCTYPE html><html>\n" +
                       "<head><title>\n" + carname + "</title></head>\n" +
                       "<body><center>\n" +
                       "<h2>\n" +  carname + " in "+ carcolor + " is chosed." + "</h2>\n" +
                       "Thank you!<br/>\n" +
                       "</center></body>\n" +
                       "</html>\n");
          }
          else{
             pw.println("<!DOCTYPE html><html>\n" +
                        "<head><title>Error</title></head>\n" +
                        "<body><center>\n" +
                        "<h2>Error</h2>\n" +
                        "Please select a car name.<br/>\n" +
                        "</center></body>\n" +
                        "</html>\n");
          }
       }
       catch(Exception e){    
          e.printStackTrace();
       }
   } 
}
