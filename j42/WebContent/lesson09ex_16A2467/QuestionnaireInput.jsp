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
<title>QuestionnaireInput</title>
</head>
<body>
<div align='center'>
<%
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
<h1>Hello Visitor! Good <%= greeting %></h1>
<form action="/j4/lesson09ex_16A2467/QuestionnaireResult.jsp" method='post'>
<table style='width:30%' border='1'>
	<tr>
		<td> </td>
		<th><h1>Questionnaire</h1></th>
	</tr>
	<tr height="10"> 
		<td> <p>Name:</p></td> 
		<td> <input type='text' name='name'/> </td> 
	</tr>
	<tr> 
		<td> <p>Email:</p></td> 
		<td> <input type='text' name='email'/> </td> 
	</tr>
	<tr> 
		<td> <p>Tel:</p></td> 
		<td> <input type='text' name='tel'/> </td> 
	</tr>
	<tr> 
		<td> <p>Gender:</p></td> 
		<td> <input type='radio' name='gender' value='male'/>Male
		<input type='radio' name='gender' value='female'/>Female </td> 
	</tr>
	<tr> 
		<td> <p>Color:</p></td> 
		<td> <input type='radio' name='color' value='black'/>Black
		<input type='radio' name='color' value='red'/>Red 
		<input type='radio' name='color' value='blue'/>Blue </td> 
	</tr>
	<tr> 
		<td> <p>Hobby:</p></td> 
		<td> <input type='checkbox' name='hobby' value='sports'/>Sports
		<input type='checkbox' name='hobby' value='music'/>Music
		<input type='checkbox' name='hobby' value='game'/>Game </td> 
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