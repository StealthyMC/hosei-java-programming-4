package j4.lesson08;
import java.util.*;
import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/lesson08/SessionCount")

public class SessionCount extends HttpServlet
{
   public void doGet(HttpServletRequest request,
                     HttpServletResponse response)
            throws ServletException, IOException {
	   //セッションの取得
	   HttpSession hs = request.getSession(true);	
	   Integer cn = (Integer) hs.getAttribute("count");	
	   Date dt = (Date) hs.getAttribute("date");	

	   String str1, str2;

       //回数の設定 
       if(cn == null){
          cn = new Integer(1);
          dt = new Date();
          str1 = "はじめてのおこしですね。";
          str2 = "";
       }
       else{
          cn = new Integer(cn.intValue() + 1);
          str1 = cn + "回目のおこしですね。";
          str2 = "（前回：" + dt + ")";
          dt = new Date();
       }
     
       //セッションの設定
       hs.setAttribute("count", cn);
       hs.setAttribute("date", dt);

       //コンテンツタイプの設定
       response.setContentType("text/html; charset=UTF-8");

       //HTML文書の書き出し
       PrintWriter pw = response.getWriter();
       pw.println(
          "<!DOCTYPE html><html>\n" +
          "<head><title>SessionCount</title></head>\n" +
          "<body><center>\n" +
          "<h2>ようこそ</h2>" +
          "<hr />\n" +
           str1 + "<br/>\n" +
           str2 + "<br/>\n" +
          "お選びください。<br/>\n" +
          "<br/>\n" +
          "<a href=\"car1.html\">乗用車</a><br/>\n" +
          "<a href=\"car2.html\">トラック</a><br/>\n" +
          "<a href=\"car3.html\">オープンカー</a><br/>\n" +
          "</center></body>\n" +
          "</html>\n");
    } 
}
