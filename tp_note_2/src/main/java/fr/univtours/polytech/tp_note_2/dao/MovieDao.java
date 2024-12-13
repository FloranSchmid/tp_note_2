package fr.univtours.polytech.tp_note_2.dao;

import java.util.List;

import fr.univtours.polytech.tp_note_2.model.MovieBean;

public interface MovieDao {

    public void createMovie(MovieBean bean);

    public List<MovieBean> getMovies();

    public MovieBean getMovie(Integer id);

    public void updateMovie(MovieBean movieBean);

    public void deleteMovie(MovieBean movieBean);
}
