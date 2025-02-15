package fr.univtours.polytech.tp_note_2.controller;

import java.io.IOException;

import fr.univtours.polytech.tp_note_2.business.MovieBusiness;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/increase")
public class IncreaseServlet extends HttpServlet {

    @Inject
    private MovieBusiness movieBusiness;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        this.movieBusiness.increaseNote(Integer.valueOf(id));

        request.getRequestDispatcher("moviesList").forward(request, response);
    }

}
