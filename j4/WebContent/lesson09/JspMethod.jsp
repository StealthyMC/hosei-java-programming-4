<%@ page contentType="text/html; charset=UTF-8" %>

<html><head>
<title>JSP Method</title>
</head><body>

<b>JSPの式/Expression: </b>
<%
int number = 100;
int sum = 0;
for (int i = 0 ; i <= number ; i++){
  sum = sum + i;
}
%>
<p>
Sum from 1 to <%= number %> = <%= sum %>
</p>

<!-- メソッドの宣言   -->
<%!
int getTotal(int num){
  int sum = 0;
  for (int i = 1 ; i <= num ; i++){
    sum += i;
  }
  return sum;
}
%>

<b>JSP Method: getTotal(int)</b>
<p>
Sum from 1 to <%= number %> = <%= getTotal(number) %>
</p>

</body>
</html>