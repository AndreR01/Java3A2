package com.example.java3assignment2;

import java.sql.SQLException;
import java.util.List;

public class DBTest {
    public static void main(String[] args) throws SQLException {
        System.out.println("Database Tester");

        List<Book> bookList = DBConnection.getAllBooks();

        for (Book book: bookList) {
            book.printBookInfo(System.out);
        }
    }
}
