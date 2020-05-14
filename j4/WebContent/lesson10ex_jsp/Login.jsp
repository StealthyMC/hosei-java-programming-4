<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
<div align='center'>
<%
String status = "";
if (request.getAttribute("status") != null)
	status = (String) request.getAttribute("status");	
%>
	<h2>Login</h2>
	<form action="http://localhost:8080/j4/lesson10ex/Process"  method="GET">
		<p>User name: <input type="text" name="user"/><p>
		<p>Password: <input type="password" name="pass"/></p>
		<p>
			Male <input type="radio" name="gender" value="male"checked/>
			Female <input type="radio" name="gender" value="female"/>
		</p>
		<p>
			Black <input type="radio" name="color" value="black"checked/>
			Blue <input type="radio" name="color" value="blue"/>
			Red <input type="radio" name="color" value="red"/>
		</p>
		<p>
			Minicar <input type="checkbox" name="car" value="minicar"/>
			Sedan <input type="checkbox" name="car" value="sedan"/>
			SUV <input type="checkbox" name="car" value="suv"/>
		</p>
		<p><input type="submit" value="Log In" name="action"/></p>
	</form>
	<%=status%>
</div>
</body>
</html>