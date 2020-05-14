<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FigureDisplay</title>
</head>
<body>
<div align='center'>
<h2>
Session started at: <% out.println(new java.util.Date()); %> 
Passed time: <%= (System.currentTimeMillis() - session.getCreationTime()) / 1000 %> seconds
</h2>
<img src="/j4/lesson09ex_16A2467/sakura1.jpg" width="20%" height="20%"/>
<img src="/j4/lesson09ex_16A2467/sakura2.jpg" width="20%" height="20%"/>
<img src="/j4/lesson09ex_16A2467/sakura3.png" width="20%" height="20%"/>
<form action="/j4/lesson09ex_16A2467/FigureVote.jsp" method='post'>
	<p>
	Click your favorite: 
	<input type="submit" name="picture" value="sakura1.jpg"/>
	<input type="submit" name="picture" value="sakura2.jpg"/>
	<input type="submit" name="picture" value="sakura3.png"/>
	</p>
</form>
</div>
</body>
</html>