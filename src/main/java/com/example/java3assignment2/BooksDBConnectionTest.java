package com.example.java3assignment2;

import java.sql.*;

/**
 * Java 3 CP3566 Spring 2022
 * Books Database Connection Test. Demonstrate a connection output to console.
 *
 * @author Andre
 */
public class BooksDBConnectionTest {

    //Set up the Database Parameters
    static final String DB_URL = "jdbc:mariadb://localhost:4000/books";
    static final String USER = "root";
    static final String PASS = "A@123456";

    public static void main(String[] args) {
        // Open a connection
        try (
                Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement statement = connection.createStatement()
        ) {
            //The Query
            String sqlQuery = "SELECT * from titles";

            //Execute the query and get the result set
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            System.out.print("ISBN \t\t\t Title");
            while (resultSet.next()) {
                System.out.printf("\n%s \t\t\t %s ",
                        resultSet.getString("isbn"), resultSet.getString("title"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}