package com.example.java3assignment2;

import java.io.*;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "LibraryData", value = "/library-data")
public class LibraryData extends HttpServlet {
    private String message;

    public void init() {
        message = "Landing page for library data";
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        //TODO Use a variable "view" to determine book or author query
        String view = request.getParameter("view");
        if (view.equals("books")) {

            List<Book> bookList = null;
            try {
                bookList = DBConnection.getAllBooks();
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("viewallbooks.jsp");
                request.setAttribute("booklist", bookList);
                //TODO add the list to the request
                requestDispatcher.forward(request, response);
            } catch (ServletException | SQLException e) {
                e.printStackTrace();
                //TODO Navigate to same error page
            }
        } else if (view.equals("authors")){
            List<Author> authorList = null;
            try {
                authorList = DBConnection.getAllAuthors();
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("viewallauthors.jsp");
                request.setAttribute("authorList", authorList);
                //TODO add the list to the request
                requestDispatcher.forward(request, response);
            } catch (SQLException | ServletException throwables) {
                throwables.printStackTrace();
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String view = request.getParameter("view");
        //TODO If this method gets too large, handle in a private method.
        if (view.equals("add_view")) {
            //TODO Handle new book
            String isbn = request.getParameter("isbn");
            String title = request.getParameter("title");
            int editionNumber = Integer.valueOf(request.getParameter("edition_Number"));
            String copyright = request.getParameter("copyright");

            try {
                DBConnection.insertBook(
                        new Book(
                                request.getParameter("isbn"),
                                request.getParameter("title"),
                                Integer.valueOf(request.getParameter("edition_Number")),
                                request.getParameter("copyright")
                        ));
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else if (view.equals("add_author")) {
            //TODO insert author
        } else {
            //Something went wrong? Do nothing?
        }


    }


    public void destroy() {
    }
}