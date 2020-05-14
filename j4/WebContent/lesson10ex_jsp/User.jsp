<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User panel</title>
</head>
<%
String username = (String) session.getAttribute("username");
String color = (String) session.getAttribute("color");
String carlist = (String) session.getAttribute("carlist");
String gender = (String) session.getAttribute("gender");

Boolean set_minicar = (Boolean) session.getAttribute("minicar");
Boolean set_sedan = (Boolean) session.getAttribute("sedan");
Boolean set_suv = (Boolean) session.getAttribute("suv");

Long money = (Long) session.getAttribute("money");

String bg_color;
if ("male".equalsIgnoreCase(gender) == true)
	bg_color = "#CAE1FF";
else
	bg_color = "#FFF0F5";

	Date date = Calendar.getInstance().getTime();
	DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	int hour = date.getHours();
	
	String greeting;
	if (hour > 5 && hour < 12)
		greeting = "Morning!";
	else if (hour >= 12 && hour < 18)
		greeting = "Afternoon!";
	else
		greeting = "Evening!";
%>

<body bgcolor=<%=bg_color%>>
<h2>Money: ¥ <%= money %></h2>
<form action="http://localhost:8080/j4/lesson10ex/Process" method="GET">
	<input type="submit" value="Logout" name="action"/>
</form>
<div align='center'>
<h2>Hello <%= username %>! Good <%= greeting %></h2>
</div>
<p>
	<input type="submit" value="Show Car List"/>
	<input type="submit" value="Show Storage"/>
</p>

<%@include  file="/lesson10ex_htmlpng/CarList.html" %>

</body>
</html>