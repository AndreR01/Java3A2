<%@ page import="java.util.List" %>
<%@ page import="com.example.java3assignment2.Book" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View all Books</title>
</head>
<body>

Book List

<!-- book list attribute is available --->

<% List<Book> bookList =  (List<Book>) request.getAttribute("booklist"); %>

<table>
    <tr>
        <th>ISBN</th>
        <th>Title</th>
        <th>Edition</th>
        <th>Copyright</th>
    </tr>

    <%
        for (Book book: bookList) {
            out.println("<tr>");
            out.println("<td>" + book.getIsbn() + "</td>");
            out.println("<td>" + book.getTitle() + "</td>");
            out.println("<td>" + book.getEditionNumber() + "</td>");
            out.println("<td>" + book.getCopyright() + "</td>");
            out.println("</tr>");
        }
    %>

</table>

</body>
</html>