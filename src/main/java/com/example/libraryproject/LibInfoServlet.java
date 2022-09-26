package com.example.libraryproject;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "LibInfoServlet", value = "/LibInfoServlet")
public class LibInfoServlet extends HttpServlet {
    /*@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }*/
    protected void service(HttpServletRequest request, HttpServletResponse response){
        String input = request.getParameter("name");

    }

}
