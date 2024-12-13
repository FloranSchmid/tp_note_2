package fr.univtours.polytech.tp_note_2.dao;

import fr.univtours.polytech.tp_note_2.model.movieDetails.WsMovieDetailsResult;

public interface MovieDetailsDao {

    public WsMovieDetailsResult getDetails(String movieTitle);
}
