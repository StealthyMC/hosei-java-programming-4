<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<title>NameFromHTML</title>
</head>
<body>
<%
  String username = request.getParameter("name");
  if (username.length() != 0) {
%>
<h3>Welcome <%= username %>!</h3>
<% }
  else {
%>
<h4>Error, please enter name.</h4>
<%
  }
%>
</body>
</html>