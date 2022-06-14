<%@ page import="java.util.List" %>
<%@ page import="com.example.java3assignment2.Book" %>
<%@ page import="com.example.java3assignment2.Author" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View all Books</title>
</head>
<body>

Book List

<!-- book list attribute is available --->

<% List<Book> bookList = (List<Book>) request.getAttribute("booklist"); %>

<table>
    <tr>
        <th>ISBN</th>
        <th>Title</th>
        <th>Edition</th>
        <th>Copyright</th>
        <th>Author(s)</th>
    </tr>

    <%
        for (Book book : bookList) {
            out.print("<tr>");
            out.print("<td>" + book.getIsbn() + "</td>");
            out.print("<td>" + book.getTitle() + "</td>");
            out.print("<td>" + book.getEditionNumber() + "</td>");
            out.print("<td>" + book.getCopyright() + "</td>");
            out.print("<td>");
            for (Author author : book.getAuthorList()) {  out.println(author.getFirstName() + " " + author.getLastName() + ", ");
            }
            out.print("</td>");
            out.print("</tr>");
        }
    %>

</table>

</body>
</html>