package com.example.java3assignment2;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Java 3 CP3566 Spring 2022
 * LibraryData class
 *
 * @author Andre
 */
@WebServlet(name = "LibraryData", value = "/library-data")
public class LibraryData extends HttpServlet {
    private String message;
    private LibraryManager libraryManager;

    public void init() {
        message = "Landing page for library data";
        libraryManager = new LibraryManager();
    }

    /**
     * Process GET requests sent to server.
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String view = request.getParameter("view");
        if (view.equals("books")) {

            List<Book> bookList = null;
            try {
                bookList = libraryManager.getBookList();
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("viewallbooks.jsp");
                request.setAttribute("booklist", bookList);
                requestDispatcher.forward(request, response);
            } catch (ServletException e) {
                errorMessage(request, response, e);
            }
        } else if (view.equals("authors")) {
            List<Author> authorList = null;
            try {
                authorList = libraryManager.getAuthorList();
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("viewallauthors.jsp");
                request.setAttribute("authorList", authorList);
                requestDispatcher.forward(request, response);
            } catch (ServletException e) {
                errorMessage(request, response, e);
            }
        }
    }

    private void errorMessage(HttpServletRequest request, HttpServletResponse response, Exception e) throws ServletException, IOException {
        e.printStackTrace();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("errorpage.jsp");
        request.setAttribute("error", e.getMessage());
        requestDispatcher.forward(request, response);
    }

    /**
     * Allow servlet to handle the POST request. Process the user's data.
     *
     * @param request isbn
     * @param response view all book OR view all authors
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String view = request.getParameter("view");

        // Add a new book to the database
        if (view.equals("add_book")) {
            try {
                libraryManager.addBook(
                        new Book(
                                request.getParameter("isbn"),
                                request.getParameter("title"),
                                Integer.valueOf(request.getParameter("edition_Number")),
                                request.getParameter("copyright")
                        ));

            } catch (SQLException e) {
                errorMessage(request, response, e);
            }

            // Return to the view of the book list including the new book
            List<Book> bookList = null;
            try {
                bookList = libraryManager.getBookList();
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("viewallbooks.jsp");
                request.setAttribute("booklist", bookList);
                 requestDispatcher.forward(request, response);
            } catch (ServletException e) {
                errorMessage(request, response, e);
            }

            // Add a new author to the database
        } else if (view.equals("add_author")) {
            try {
                Author createdAuthor = libraryManager.addAuthor(
                        new Author(
                                0,
                                request.getParameter("firstName"),
                                request.getParameter("lastName")
                        ));
                System.out.println("Created author ID is: " + createdAuthor.getAuthorID());
            } catch (SQLException e) {
                errorMessage(request, response, e);
            }

            // Return to view of the author list including new author
            List<Author> authorList = null;
            try {
                authorList = libraryManager.getAuthorList();
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("viewallauthors.jsp");
                request.setAttribute("authorList", authorList);
                requestDispatcher.forward(request, response);
            } catch (ServletException e) {
                errorMessage(request, response, e);
            }

        } else {
            //Something went wrong? Do nothing?
        }
    }


    /**
     * Release the datatbase object created in the init method.
     */
    public void destroy() {
    }
}