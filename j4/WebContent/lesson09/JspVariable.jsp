<%@ page contentType="text/html; charset=UTF-8" %>

<html><head><title>Count Variables</title></head>
<body>
<h4>Global and Local Variables</h4>

<!-- 共に変数の宣言   -->
<%!
int globalCount = 0;
%>
<!-- ローカル変数の宣言   -->
<%
int localCount = 0;

globalCount++;
localCount++;
%>

<p>
localCount inside scriptlet = <%= localCount %>
</p>

<p>
globalCount by all clients = <%= globalCount %>
</p>

</body>
</html>