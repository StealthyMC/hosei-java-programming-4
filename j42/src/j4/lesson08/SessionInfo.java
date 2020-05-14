package j4.lesson08;

import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/lesson08/SessionInfo")

public class SessionInfo extends HttpServlet {
	int countAll = 0;
    public void doGet(HttpServletRequest request,
                     HttpServletResponse response)
            throws ServletException, IOException {
	   //セッションの取得
	   HttpSession hs = request.getSession(true);
	   //Integer cn = (Integer) hs.getAttribute("count");	
	   countAll++;

	   String str0, str1, str2, str3, str4, str5, totalVisit;

       //回数の設定 
       if(hs.isNew()){    	   
          hs.setAttribute("count", 1);
          str1 = "はじめてのおこしですね。";
          str2 = "（New Session)";
       }
       else{
    	  int cn = (Integer) hs.getAttribute("count");
    	  cn++;
    	  //cn = new Integer(cn.intValue() + 1);
    	  hs.setAttribute("count", cn);
          str1 = cn + "回目のおこしですね。";
          Date lastAccess = new Date(hs.getLastAccessedTime());    
          str2 = "（前回：" + lastAccess + ")";  
          hs.setMaxInactiveInterval(5);
       }
       
       str3 = "Your Session ID: " + hs.getId();
       long createTime = hs.getCreationTime();
       Date createDate = new Date(createTime);
       str4 = "Your session was created at " + createDate; 
       int timeout = hs.getMaxInactiveInterval();
       str5 = "Session Interval: " + timeout + " seconds";
       //if (cn == 10) hs.invalidate();
       
       totalVisit = "Total Vistors: " + countAll;

       //コンテンツタイプの設定
       response.setContentType("text/html; charset=UTF-8");

       //HTML文書の書き出し
       PrintWriter pw = response.getWriter();
       pw.println(
          "<!DOCTYPE html><html>\n" +
          "<head><title>SessionCount</title></head>\n" +
          "<body><center>\n" +
          "<hr />\n" +
          "<b>" + str1 + "</b><br/>\n" +
           str2 + "<br/>\n" +
           str3 + "<br/>\n" +
           str4 + "<br/>\n" +   
           "<b>" + str5 + "</b><br/>\n" +   
           "<hr />\n" +
           "<h3>" + totalVisit + "</h3>" +
          "</center></body>\n" +
          "</html>\n");
    } 
}

