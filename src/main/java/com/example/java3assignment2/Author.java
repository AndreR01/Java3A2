package com.example.java3assignment2;

import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Java 3 CP3566 Spring 2022
 * Author class to hold author information
 *
 * @author Andre
 */
public class Author {
    public int authorID;
    public String firstName;
    public String lastName;
    private List<Book> bookList;

    /**
     * Constructor for an author object
     * @param authorID
     * @param firstName
     * @param lastName
     */
    public Author(int authorID, String firstName, String lastName) {
        this.authorID = authorID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bookList = new LinkedList<>();
    }

    /**
     * Get the authorID.
     * @return authorID
     */
    public int getAuthorID() {
        return authorID;
    }

    /**
     * Sets the authorID.
     * @param authorID
     */
    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    /**
     * Get the author's first name.
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the author's first name.
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get the author's last name.
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the author's last name.
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get at linked list of books.
     * @return bookList
     */
    public List<Book> getBookList() {
        return bookList;
    }

    /**
     * Set a linked list of books associated with the author.
     * @param bookList
     */
    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    /**
     * Prints the author information and books associated with the author.
     * @param printStream
     */
    public void printAuthorsInfo(PrintStream printStream) {
        printStream.printf("\nAUTHOR ID: %d \t\t FIRST NAME: %-10s \t\t LAST NAME: %-10s",
                this.getAuthorID(), this.getFirstName(), this.getLastName());
        bookList.stream().forEach((k) -> {
            System.out.printf("\tISBN: %-12s TITLE: %-60s EDITION: %-5s", k.getIsbn(),k.getTitle(),k.getEditionNumber());
        });
    }

    /**
     * Prints the author's first and last name.
     * @param printStream
     */
    public void printAuthorIDAndNameOnly(PrintStream printStream){
        printStream.printf("\nAUTHOR ID: %d \t\t FIRST NAME: %-10s \t\t LAST NAME: %-10s",
                this.getAuthorID(), this.getFirstName(), this.getLastName());
    }
}
