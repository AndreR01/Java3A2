package com.example.java3assignment2;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Java 3 CP3566 Spring 2022
 * DB Connection class connects to the database; retrieves all book; retrieves all authors;
 * inserts a new book; and inserts a new author
 *
 * @author Andre
 */
//TODO close the conn?
public class DBConnection {

    /**
     * Retrieve all books from the database into a LinkedList.
     * Note: this method is dangerous if the database is large. In our example it isn't.
     *
     * @return List of Book objects
     */
    public static List<Book> getAllBooks() throws SQLException {
        LinkedList<Book> bookList = new LinkedList<>();
        Connection connection = getBooksDBConnection();
        Statement statement = connection.createStatement();
        String sqlQuery = "SELECT * from " + BooksDatabaseSQL.BOOK_TABLE_NAME;

        ResultSet resultSet = statement.executeQuery(sqlQuery);

        while (resultSet.next()) {
            bookList.add(
                    new Book(
                            resultSet.getString(BooksDatabaseSQL.BOOK_COL_NAME_ISBN),
                            resultSet.getString(BooksDatabaseSQL.BOOK_COL_NAME_TITLE),
                            resultSet.getInt(BooksDatabaseSQL.BOOK_COL_NAME_EDITION_NUMBER),
                            resultSet.getString(BooksDatabaseSQL.BOOK_COL_NAME_COPYRIGHT)
                    )
            );
        }
        return bookList;
    }

    /**
     * Insert book into the database.
     *
     * @param book Book
     * @throws SQLException
     */
    public static void insertBook(Book book) throws SQLException {
        Connection connection = getBooksDBConnection();

        String sqlQuery = "INSERT INTO " + BooksDatabaseSQL.BOOK_TABLE_NAME + " VALUES (?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        System.out.println("Statement prepared");
        //The 4 values are the books attributes
        preparedStatement.setString(1, book.getIsbn());
        preparedStatement.setString(2, book.getTitle());
        preparedStatement.setInt(3, book.getEditionNumber());
        preparedStatement.setString(4, book.getCopyright());
        System.out.println("Statement populated");
        int i = preparedStatement.executeUpdate();
        System.out.println("Records inserted" + i);
    }

    /**
     * Insert an author into the database.
     *
     * @param author
     * @throws SQLException
     */
    public static void insertAuthor(Author author) throws SQLException {
        Connection connection = getBooksDBConnection();

        String sqlQuery = "INSERT INTO " + BooksDatabaseSQL.AUTHOR_TABLE_NAME + " VALUES (default,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

        //The 2 values are the author attributes
        preparedStatement.setString(1, author.getFirstName());
        preparedStatement.setString(2, author.getLastName());
        int i = preparedStatement.executeUpdate();
        System.out.println("Records inserted" + i);
    }


    /**
     * Creates a list of authors from the result set of the author's table.
     *
     * @return authorList
     * @throws SQLException
     */
    public static List<Author> getAllAuthors() throws SQLException {
        LinkedList<Author> authorList = new LinkedList<>();
        Connection connection = getBooksDBConnection();
        Statement statement = connection.createStatement();
        String sqlQuery = "SELECT * from " + BooksDatabaseSQL.AUTHOR_TABLE_NAME;

        ResultSet resultSet = statement.executeQuery(sqlQuery);

        while (resultSet.next()) {
            authorList.add(
                    new Author(
                            resultSet.getInt(BooksDatabaseSQL.AUTHOR_COL_NAME_AUTHORID),
                            resultSet.getString(BooksDatabaseSQL.AUTHOR_COL_NAME_FIRSTNAME),
                            resultSet.getString(BooksDatabaseSQL.AUTHOR_COL_NAME_LASTNAME)
                    )
            );
        }
        return authorList;
    }

    /**
     * Get a connection to the Books Database - details in the inner class Books Database SQL
     *
     * @return connection
     * @throws SQLException
     */
    private static Connection getBooksDBConnection() throws SQLException {
        try {
            Class.forName("org.mariadb.jdbc.Driver").newInstance();
            System.out.println("Option 1: Find the class worked!");
        } catch (ClassNotFoundException ex) {
            System.err.println("Error: unable to load driver class!");
        } catch (IllegalAccessException ex) {
            System.err.println("Error: access problem while loading!");
        } catch (InstantiationException ex) {
            System.err.println("Error: unable to instantiate driver!");
        }
        //TODO do i need conn.close
        return DriverManager.getConnection(BooksDatabaseSQL.DB_URL, BooksDatabaseSQL.USER, BooksDatabaseSQL.PASS);
    }

    /**
     * Creates a list of authorISBN's from a result set on the authorISBN table.
     *
     * @return authorISBN
     * @throws SQLException
     */
    public static List<AuthorISBN> getAllISBN() throws SQLException {
        LinkedList<AuthorISBN> authorISBN = new LinkedList<AuthorISBN>();

        Connection connection = getBooksDBConnection();
        Statement statement = connection.createStatement();
        String sqlQuery = "SELECT * from " + BooksDatabaseSQL.AUTHORISBN_TABLE_NAME;

        ResultSet resultSet = statement.executeQuery(sqlQuery);

        while (resultSet.next()) {
            authorISBN.add(
                    new AuthorISBN(
                            resultSet.getInt(BooksDatabaseSQL.AUTHORISBN_COL_NAME_AUTHORID),
                            resultSet.getString(BooksDatabaseSQL.AUTHORISBN_COL_NAME_ISBN)
                    )
            );
        }
        return authorISBN;
    }


    /**
     * Simple inner class to abstract all the specific SQL Information
     */
    private class BooksDatabaseSQL {

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

        public static final String AUTHOR_TABLE_NAME = "authors";
        public static final String AUTHOR_COL_NAME_AUTHORID = "authorID";
        public static final String AUTHOR_COL_NAME_FIRSTNAME = "firstName";
        public static final String AUTHOR_COL_NAME_LASTNAME = "lastName";
        public static final String AUTHORISBN_TABLE_NAME = "authorisbn";
        public static final String AUTHORISBN_COL_NAME_AUTHORID = "authorID";
        public static final String AUTHORISBN_COL_NAME_ISBN = "isbn";
    }
}

//TODO FROM MEETING WITH JOSH build the web forms. hard code into servlet