<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<title>To JSP</title>
</head>
<body><center>
<%!
int countE = 1, countJ = 1;
%>
<% 
   String name = request.getParameter("name");
   if(name.length() == 0) {
%>
     <jsp:forward page="error.html"/>
<% }
   else {
     String language = request.getParameter("language");
     String replyMsg, number;
     if (language.equalsIgnoreCase("Eng")){
        replyMsg = "Welcome " + name + "!";
        number = "Your Number: E-" + countE++;
      }
     else {
        replyMsg = "ようこそ, " + name + "!";
        number = "あなたの番号: J-" + countJ++;
      }
%>
   <h3> <%= replyMsg %> </h3>
   <h3> <%= number %> </h3> 
<%  
  }
%>   

</body></center>
</html>