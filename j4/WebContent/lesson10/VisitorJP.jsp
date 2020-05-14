<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<title>To JSP</title>
</head>
<body><center>
<%!
int count = 1;
%>
<% 
   String name = request.getParameter("name");
%>
<h3> ようこそ, <%= name %> !</h3>
<h3> あなたの番号: J-<%= count++ %> </h3>  

</body></center>
</html>