<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error Page</title>
</head>
<body>
    <% out.println(request.getAttribute("error")); %>
</body>
</html>
