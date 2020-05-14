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
<title>QuestionnaireResult</title>
</head>
<body>
<div align='center'>
<%
	// store values of the user in Strings
	String user = request.getParameter("name");
	String email = request.getParameter("email");
	String tel = request.getParameter("tel");
	String gender = request.getParameter("gender");
	String color = request.getParameter("color");
	String[] hobby = request.getParameterValues("hobby");
	// get session id
	HttpSession hs = request.getSession(true);
	// get submission time
	long createTime = hs.getCreationTime();
	Date createDate = new Date(createTime);
	int sessionInterval = hs.getMaxInactiveInterval();
	
	// send variables to the gulag
	session.setAttribute("name", user);
	session.setAttribute("email", email);
	session.setAttribute("tel", tel);
	session.setAttribute("gender", gender);
	session.setAttribute("color", color);
	session.setAttribute("hobby", hobby);
%>
<font color=<%=color%>>
<h3>User Name: <%= user %></h3>
<h3>Email: <%= email %></h3>
<h3>Telephone Number: <%= tel %></h3>
<h3>Gender: <%= gender %></h3>
<h3>Favorite Color: <%= color %></h3>
<h3>
Hobby: 
<%
if (hobby != null)	// only print hobbies if they are selected
{
	for (int i = 0; i < hobby.length; i++)
	{
		out.println(hobby[i] + " ");	// print each checked value
	}
}
%>
</h3>
<h3>Input/Edit Time: <%= new Date(session.getCreationTime()) %></h3>
<h3>Session ID: <%= hs.getId() %></h3>
</font>
<hr>
<form action="/j4/lesson09ex_16A2467/QuestionnaireEdit.jsp" method='post'>
	<input type='submit' value='Edit Questionnaire'/>
</form>
</div>
</body>
</html>