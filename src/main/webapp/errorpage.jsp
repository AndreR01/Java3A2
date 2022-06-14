<%--
  Created by IntelliJ IDEA.
  User: Andre
  Date: 6/13/2022
  Time: 11:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error Page</title>
</head>
<body>
    <% out.println(request.getAttribute("error")); %>
</body>
</html>
