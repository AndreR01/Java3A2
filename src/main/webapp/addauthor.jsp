
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Author to the Database!</title>
</head>
<body>

Add an author
<!--
public int authorID;
public String firstName;
public String lastName;
-->

<h1>Add an Author</h1>

<form action= "library-data" method = "POST">
    AuthorID: <input type = "text" name = "authorID"> <br />
    First name: <input type = "text" name = "firstName" /> <br />
    Last name: <input type = "text" name = "lastName" />
    <input type = "hidden" id="view" name="view" value = "add_author">
    <input type = "submit"  value = "Submit" />
</form>


<a href="index.jsp">Back to Main Menu</a>

</body>
</html>
