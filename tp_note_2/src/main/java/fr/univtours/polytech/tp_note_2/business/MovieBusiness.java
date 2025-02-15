package fr.univtours.polytech.tp_note_2.business;

import java.util.List;

import fr.univtours.polytech.tp_note_2.model.MovieBean;

public interface MovieBusiness {

    public void addMovie(MovieBean movieBean);

    public List<MovieBean> getMovies();

    public MovieBean getMovie(Integer id);

    public void updateMovie(MovieBean movieBean);

    public void deleteMovie(Integer id);

    public void increaseNote(Integer id);

    public void decreaseNote(Integer id);
}
