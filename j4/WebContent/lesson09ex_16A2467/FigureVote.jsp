<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<div align='center'>
<jsp:include page="FigureDisplay.jsp" flush="true"></jsp:include>
<h3>
	Your favorite picture is: <%= request.getParameter("picture") %>
	<%! int[] selected = new int[3]; int mostSelected = 0; int popular = 0;%>
	<% 
		// Increase view counter for picture
		if ("sakura1.jpg".equals(request.getParameter("picture")))
		{
			selected[0]++;
			out.println("<img src='/j4/lesson09ex_16A2467/sakura1.jpg' width='10%' height='10%'/>");
		}
		if ("sakura2.jpg".equals(request.getParameter("picture")))
		{
			selected[1]++;
			out.println("<img src='/j4/lesson09ex_16A2467/sakura2.jpg' width='10%' height='10%'/>");
		}
		if ("sakura3.png".equals(request.getParameter("picture")))
		{
			selected[2]++;
			out.println("<img src='/j4/lesson09ex_16A2467/sakura3.png' width='10%' height='10%'/>");
		}
		// Check which picture is selected the most
		for (int i = 0; i < selected.length; i++)
		{
			if (selected[i] > popular)
			{
				popular = selected[i];
				mostSelected = i;
			}
		}
	%>
</h3>
<h3>
	The most selected flower by all clients is: 
	<% 
		switch (mostSelected)
		{
		case 0:
			out.println("sakura1.jpg" + "<img src='/j4/lesson09ex_16A2467/sakura1.jpg'");
			break;
		case 1:
			out.println("sakura2.jpg" + "<img src='/j4/lesson09ex_16A2467/sakura2.jpg'");
			break;
		case 2:
			out.println("sakura3.png" + "<img src='/j4/lesson09ex_16A2467/sakura3.png'");
			break;
		}
		out.println(" width='10%' height='10%'/></h3>");
		out.println("<h3>Total selected times: " + selected[mostSelected]);
	%>
</h3>
</div>
</body>
</html>	