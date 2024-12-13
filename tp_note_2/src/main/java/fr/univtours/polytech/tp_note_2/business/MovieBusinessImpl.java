package fr.univtours.polytech.tp_note_2.business;

import java.util.List;

import fr.univtours.polytech.tp_note_2.dao.MovieDao;
import fr.univtours.polytech.tp_note_2.dao.MovieDetailsDao;
import fr.univtours.polytech.tp_note_2.model.MovieBean;
import fr.univtours.polytech.tp_note_2.model.movieDetails.WsMovieDetailsResult;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class MovieBusinessImpl implements MovieBusiness {

    @Inject
    private MovieDao movieDao;

    @Inject
    MovieDetailsDao movieDetailsDao;

    @Override
    public void addMovie(MovieBean movieBean) {
        movieDao.createMovie(movieBean);
    }

    @Override
    public List<MovieBean> getMovies() {
        List<MovieBean> movies = this.movieDao.getMovies();

        for (MovieBean movieBean : movies) {
            movieBean.setIncreaseURL("<button><a href=\"increase?id=" + movieBean.getId() + "\">+</a></button>");
            movieBean.setDecreaseURL("<button><a href=\"decrease?id=" + movieBean.getId() + "\">-</a></button>");

            String movieTitle = movieBean.getTitle();

            WsMovieDetailsResult result = this.movieDetailsDao.getDetails(movieTitle);

            movieBean.setActors(result.getDescription().get(0).getActors());
            movieBean.setYear(result.getDescription().get(0).getYear());
            movieBean.setImgPoster(result.getDescription().get(0).getImgPoster());
        }

        return movies;
    }

    @Override
    public MovieBean getMovie(Integer id) {
        MovieBean movieBean = movieDao.getMovie(id);

        WsMovieDetailsResult result = this.movieDetailsDao.getDetails(movieBean.getTitle());

        movieBean.setActors(result.getDescription().get(0).getActors());
        movieBean.setYear(result.getDescription().get(0).getYear());
        movieBean.setImgPoster(result.getDescription().get(0).getImgPoster());

        return movieBean;
    }

    @Override
    public void updateMovie(MovieBean movieBean) {
        movieDao.updateMovie(movieBean);
    }

    @Override
    public void deleteMovie(Integer id) {
        MovieBean movieBean = getMovie(id);
        movieDao.deleteMovie(movieBean);
    }

    @Override
    public void increaseNote(Integer id) {
        MovieBean movieBean = this.movieDao.getMovie(id);
        Integer note = movieBean.getNote();
        if (note == null) {
            movieBean.setNote(1);
        } else if (note < 5) {
            movieBean.setNote(note + 1);
        }
    }

    @Override
    public void decreaseNote(Integer id) {
        MovieBean movieBean = this.movieDao.getMovie(id);
        Integer note = movieBean.getNote();
        if (note == null) {
            movieBean.setNote(5);
        } else if (note > 1) {
            movieBean.setNote(note - 1);
        }
    }
}
