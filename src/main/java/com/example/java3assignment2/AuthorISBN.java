package com.example.java3assignment2;

/**
 * Java 3 CP3566 Spring 2022
 * AuthorISBN class is the bridging class between author and books.
 *
 * @author Andre
 */
public class AuthorISBN {
    public int authorID;
    public String isbn;

    /**
     * Constructor to initialize an AuthorISBN object.
     * @param authorID
     * @param isbn
     */
    public AuthorISBN(int authorID, String isbn) {
        this.authorID = authorID;
        this.isbn = isbn;
    }
}
