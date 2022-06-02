package com.example.java3assignment2;

import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Java 3 CP3566 Spring 2022
 * Book class to hold information about the book.
 *
 * @author Andre
 */
public class Book {

    public String isbn;
    public String title;
    public int editionNumber;
    public String copyright;
    private List<Author> authorList;

    /**
     * Constructor for the book object.
     * @param isbn
     * @param title
     * @param editionNumber
     * @param copyright
     */
    public Book(String isbn, String title, int editionNumber, String copyright) {
        this.isbn = isbn;
        this.title = title;
        this.editionNumber = editionNumber;
        this.copyright = copyright;
        this.authorList = new LinkedList<>();
    }

    /**
     * Get the book's isbn.
     * @return  isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Set the book's isbn.
     * @param isbn
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Get the book's title.
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the book's title.
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get the book's edition number.
     * @return editionNumber
     */
    public int getEditionNumber() {
        return editionNumber;
    }

    /**
     * Set the book's edition number.
     * @param editionNumber
     */
    public void setEditionNumber(int editionNumber) {
        this.editionNumber = editionNumber;
    }

    /**
     * Get the book's copyright.
     * @return copyright
     */
    public String getCopyright() {
        return copyright;
    }

    /**
     * Set the book's copyright.
     * @param copyright
     */
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    /**
     * Get the author list associated with the book.
     * @return authorList
     */
    public List<Author> getAuthorList() {
        return authorList;
    }

    /**
     * Set the author list associated with the book.
     * @param authorList
     */
    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }

    /**
     * Prints the book information and authors associated with it.
     * @param printStream
     */
    public void printBookInfo(PrintStream printStream) {
        printStream.printf("\nISBN: %s \t TITLE: %-60s \t EDITION: %d \t COPYRIGHT: %s",
                getIsbn(), getTitle(), getEditionNumber(), getCopyright());
        authorList.stream().forEach((k) -> {
            System.out.printf("\tFIRST NAME: %-15s LAST NAME: %-20s", k.getFirstName(),k.getLastName());
        });
    }
}