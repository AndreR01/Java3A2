<%@ page import="java.util.List" %>
<%@ page import="com.example.java3assignment2.Author" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View all Authors</title>
</head>
<body>

Authors List

<!-- author list attribute is available --->

<% List<Author> authorList =  (List<Author>) request.getAttribute("authorList"); %>

<table>
    <tr>
        <th>AuthorID</th>
        <th>First Name</th>
        <th>Last Name</th>
    </tr>

    <%
        for (Author author: authorList) {
            out.println("<tr>");
            out.println("<td>" + author.getAuthorID() + "</td>");
            out.println("<td>" + author.getFirstName() + "</td>");
            out.println("<td>" + author.getLastName() + "</td>");
            out.println("</tr>");
        }
    %>

</table>

</body>
</html>