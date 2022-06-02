package com.example.java3assignment2;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DBConnection {

    /**
     * Retrieve all of the books from the database into a LinkedList.
     * Note: this method is dangerous if the database is large. In our example it isn't.
     * @return List of Book objects
     */
    public static List<Book> getAllBooks(){
        LinkedList bookList = new LinkedList();
        try (
                Connection connection = getBooksDBConnection();
                Statement statement = connection.createStatement();
        ) {
            String sqlQuery = "SELECT * from " + BooksDatabaseSQL.BOOK_TABLE_NAME;
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while(resultSet.next()) {
                bookList.add(
                        new Book(
                                resultSet.getString(BooksDatabaseSQL.BOOK_COL_NAME_ISBN),
                                resultSet.getString(BooksDatabaseSQL.BOOK_COL_NAME_TITLE),
                                resultSet.getInt(BooksDatabaseSQL.BOOK_COL_NAME_EDITION_NUMBER),
                                resultSet.getString(BooksDatabaseSQL.BOOK_COL_NAME_COPYRIGHT)
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }



    /**
     * Get a connection to the Books Database - details in the inner class Books Database SQL
     * @return connection
     * @throws SQLException
     */
    private static Connection getBooksDBConnection() throws SQLException {
        return DriverManager.getConnection(BooksDatabaseSQL.DB_URL, BooksDatabaseSQL.USER, BooksDatabaseSQL.PASS);
    }

    /**
     * Simple inner class to abstract all the specific SQL Information
     */
    private class BooksDatabaseSQL{

        //Login information
        public static final String DB_URL = "jdbc:mariadb://localhost:4000/books";
        public static final String USER = "root";
        public static final String PASS = "A@123456";

        //Book Table Information
        public static final String BOOK_TABLE_NAME = "titles";
        public static final String BOOK_COL_NAME_ISBN = "isbn";
        public static final String BOOK_COL_NAME_TITLE = "title";
        public static final String BOOK_COL_NAME_EDITION_NUMBER = "editionNumber";
        public static final String BOOK_COL_NAME_COPYRIGHT = "copyright";

    }

}