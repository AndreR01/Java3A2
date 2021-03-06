package com.example.java3assignment2;


import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Java 3 CP3566 Spring 2022
 * Library manager
 *
 * @author Andre
 */
public class LibraryManager {
    private List<Author> authorList;
    private List<Book> bookList;
    private List<AuthorISBN> authorISBN;


    /**
     *  Simple constructor to initialize a Library Manager object.
     */
    public LibraryManager() {
        reloadFromDataSource();
    }

    /**
     * Links books and authors together to be managed in the BookDatabase Manager.
     */
    public void reloadFromDataSource() {

        try {
            authorList = DBConnection.getAllAuthors();
            bookList = DBConnection.getAllBooks();
            authorISBN = DBConnection.getAllISBN();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Author author : authorList) {
            List<String> bookISBNs = new LinkedList<>();
            for (AuthorISBN ai : authorISBN) {
                if (ai.authorID == author.authorID) {
                    bookISBNs.add(ai.isbn);
                }
            }
            for (Book b : bookList) {
                if (bookISBNs.contains(b.isbn)) {
                    author.getBookList().add(b);
                    b.getAuthorList().add(author);
                }
            }
        }
    }

    /**
     * Get the author list.
     *
     * @return authorList
     */
    public List<Author> getAuthorList() {
        return authorList;
    }

    /**
     * Get the book list.
     *
     * @return bookList
     */
    public List<Book> getBookList() {
        return bookList;
    }

    /**
     * Add book to the database and refresh table
     *
     * @param book
     * @throws SQLException
     */
    public void addBook(Book book) throws SQLException {
        System.out.println("Adding book" + book.getTitle());
        DBConnection.insertBook(book);
        reloadFromDataSource();
    }

    /**
     * Add author to the database and refresh table
     *
     * @param author
     * @return Author
     * @throws SQLException
     */
    public Author addAuthor(Author author) throws SQLException {
        DBConnection.insertAuthor(author);
        reloadFromDataSource();
        Iterator<Author> iterator = authorList.iterator();
        while (iterator.hasNext()) {
            Author currentAuthor = iterator.next();
            if (currentAuthor.getFirstName().equals(author.getFirstName())
                    && currentAuthor.getLastName().equals(author.getLastName())) {
                return currentAuthor;
            }
        }
        return new Author(0, "J", "Doe");
    }
}


