package fr.univtours.polytech.tp_note_2.controller;

import java.io.IOException;
import java.util.List;

import fr.univtours.polytech.tp_note_2.business.MovieBusiness;
import fr.univtours.polytech.tp_note_2.model.MovieBean;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/moviesList")
public class MoviesListServlet extends HttpServlet {

    @Inject
    private MovieBusiness movieBusiness;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<MovieBean> moviesList = movieBusiness.getMovies();

        request.setAttribute("MOVIES_LIST", moviesList);

        request.getRequestDispatcher("movieList.jsp").forward(request, response);
    }

}
