package com.example.java3assignment2;

import java.sql.*;

/**
 * Books Database Connection Test. Demonstrate a connection and a simple select and dump to the console.
 *
 * @author Josh
 */
public class BooksDBConnectionTest {

    //Set up the Database Parameters
    static final String DB_URL = "jdbc:mariadb://localhost:4000/books";
    //TODO Customize to your setup
    static final String USER = "root";
    static final String PASS = "A@123456";

    public static void main(String[] args) {
        // Open a connection
        try(
                Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement statement = connection.createStatement();
        ){
            String sqlQuery = "SELECT * from titles";          //The Query

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