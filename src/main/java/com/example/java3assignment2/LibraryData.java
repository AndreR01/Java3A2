package com.example.java3assignment2;

import java.io.*;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "librarydata", value = "/library-data")
public class LibraryData extends HttpServlet {
    private String message;

    public void init() {
        message = "Landing page for library data";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        //TODO Use a variable "view" to determine book or author query

        List<Book> bookList = DBConnection.getAllBooks();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("viewallbooks.jsp");

        request.setAttribute("booklist", bookList);

        //TODO add the list to the request
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }

    }

    public void destroy() {
    }
}