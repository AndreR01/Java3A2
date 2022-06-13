package com.example.java3assignment2;


import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class LibraryManager {
    private List<Author> authorList;
    private List<Book> bookList;
    private List<AuthorISBN> authorISBN;


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
     */
    public void addBook(Book book) throws SQLException {
        DBConnection.insertBook(book);
        reloadFromDataSource();
    }

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


