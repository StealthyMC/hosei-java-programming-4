<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.Date" %>

<html><head>
<title>HelloJSPDirective</title>
</head><body>
<h3>Hello, JSP with Directives.</h3>

<b>Import: </b>
<% out.println(new Date()); %>
<p></p>
<b>Include: </b>
<%@ include file="DateDisp.jsp" %>

</body></html>