<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<title>Include & Forward</title>
</head>
<body>
<%
  String number = request.getParameter("input");
  if (number.equalsIgnoreCase("1")) {
%>
    <jsp:forward page="Hello.jsp"/>
<% }
  else if (number.equalsIgnoreCase("2")){
%>
   <jsp:forward page="NameFromHTML.html"/>
<%
  } else {
%>
   <jsp:include page="IncludePage.txt" flush="true"/>
   <jsp:include page="IncludePage.html" flush="true"/>
<%@ include file="DateDisp.jsp" %>
<%
  }
%>

</body>
</html>