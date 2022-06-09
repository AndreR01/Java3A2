
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add a book to the database!</title>
</head>
<body>

Add a Book
<!--
    public String isbn;
    public String title;
    public int editionNumber;
    public String copyright;
-->
<h1>Add a Book</h1>

<form action= "library-data" method = "POST">
    ISBN: <input type = "text" name = "isbn"> <br />
    Title: <input type = "text" name = "title" /> <br />
    Edition Number: <input type = "text" name = "edition_Number" /> <br />
    Copyright: <input type = "text" name = "copyright" />
    <input type = "hidden" id="view" name="view" value = "add_book">
    <input type = "submit"  value = "Submit" />
</form>

<a href="index.jsp">Back to Main Menu</a>

</body>
</html>
