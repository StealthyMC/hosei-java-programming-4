<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.Date" %>

<!DOCTYPE html>
<html>
<head>
<title>JSP Session</title>
</head>
<body>
<%! int totalVisit = 0; %>
<%
  if (session.isNew()) {
	  String username = request.getParameter("name");
	  session.setAttribute("sname", username);
	  session.setAttribute("sid", new Date().getTime());
	  session.setAttribute("count", 1);
	  out.println("Hello " + session.getAttribute("sname"));
	  out.println("<br>Set Session ID: " + session.getAttribute("sid"));
	  out.println("<br>JSP Session ID: " + session.getId());
	  int timeout = session.getMaxInactiveInterval();
	  out.println("<br>Default Timeout: " + timeout);	
	  session.setMaxInactiveInterval(10);
	  timeout = session.getMaxInactiveInterval();
	  out.println("<br>Reset Timeout: " + timeout + " seconds");  
     }
  
  else {
	  out.println("Weclome back, " + session.getAttribute("sname"));
	  out.println("<br>Your session id: " + session.getAttribute("sid"));
      long createTime = session.getCreationTime();
      Date createDate = new Date(createTime);
	  out.println("<br>Your session created: " + createDate);
	  Date lastAccess = new Date(session.getLastAccessedTime());
	  out.println("<br>Your last access: " + lastAccess);
	  //Integer cn = (Integer) session.getAttribute("count");
	  int cn = (Integer) session.getAttribute("count");
	  cn++;
	  session.setAttribute("count", cn);
	  out.println("<br>Your total access: " + cn);	  
  }

  totalVisit++;
%>
<hr>
<%= "Total Visitors to this JSP: "  + totalVisit%>

</body>
</html>