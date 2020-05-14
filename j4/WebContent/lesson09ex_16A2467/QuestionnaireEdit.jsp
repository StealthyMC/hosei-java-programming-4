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
<title>QuestionnaireEdit</title>
</head>
<body>
<div align='center'>
<%
	Date date = Calendar.getInstance().getTime();
	DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	HttpSession hs = request.getSession(true);
	
	int hour = date.getHours();
	
	String user = (String) session.getAttribute("name");
	String email = (String) session.getAttribute("email");
	String tel = (String) session.getAttribute("tel");
	String gender = (String) session.getAttribute("gender");
	String color = (String) session.getAttribute("color");
	
	String[] hobby = (String[]) session.getAttribute("hobby");
	int[] hobbyValues = new int[3];
	
	if (hobby != null)
	{
		for(String i:hobby)
		{
			if ("sports".equals(i))
				hobbyValues[0] = 1;
			if ("music".equals(i))
				hobbyValues[1] = 1;
			if ("game".equals(i))
				hobbyValues[2] = 1;
		}
	}

	
	String greeting;
	if (hour > 5 && hour < 12)
		greeting = "Morning!";
	else if (hour >= 12 && hour < 18)
		greeting = "Afternoon!";
	else
		greeting = "Evening!";
	
%>
<h1>Hello <%= user %>! Good <%= greeting %> </h1>
<form action="/j4/lesson09ex_16A2467/QuestionnaireResult.jsp" method='post'>
<table style='width:30%' border='1' cellpadding="0">
	<tr>
		<td> </td>
		<th><h1>Questionnaire</h1></th>
	</tr>
	<tr height="10"> 
		<td> <p>Name:</p></td> 
		<td> <input type="text" name="name" value="<%= user %>" /> </td> 
	</tr>
	<tr> 
		<td> <p>Email:</p></td> 
		<td> <input type="text" name="email" value="<%= email %>" /> </td> 
	</tr>
	<tr> 
		<td> <p>Tel:</p></td> 
		<td> <input type="text" name="tel" value="<%= tel %>" />  </td> 
	</tr>
	<tr> 
		<td> <p>Gender:</p></td> 
		<td> <input type='radio' name='gender' value='male' 
		<% if ("male".equals(gender)) out.println("checked"); %> />Male
		<input type='radio' name='gender' value='female'
		<% if ("female".equals(gender)) out.println("checked"); %> />Female </td> 
	</tr>
	<tr> 
		<td> <p>Color:</p></td> 
		<td> <input type='radio' name='color' value='black'
		<% if ("black".equals(color)) out.println("checked"); %> />Black
		<input type='radio' name='color' value='red'
		<% if ("red".equals(color)) out.println("checked"); %> />Red 
		<input type='radio' name='color' value='blue'
		<% if ("blue".equals(color)) out.println("checked"); %> />Blue </td> 
	</tr>
	<tr> 
		<td> <p>Hobby:</p></td> 
		<td> <input type='checkbox' name='hobby' value='sports'
		<% if (hobbyValues[0] == 1) out.println("checked"); %> />Sports
		<input type='checkbox' name='hobby' value='music'
		<% if (hobbyValues[1] == 1) out.println("checked"); %> />Music
		<input type='checkbox' name='hobby' value='game'
		<% if (hobbyValues[2] == 1) out.println("checked"); %> />Game </td> 
	</tr>
	<tr> 
		<td> </td> 
		<td> <input type='reset' value='reset'/>
		<input type='submit' value='submit'/></td> 
	</tr>
</table>
</form>
</div>
</body>
</html>