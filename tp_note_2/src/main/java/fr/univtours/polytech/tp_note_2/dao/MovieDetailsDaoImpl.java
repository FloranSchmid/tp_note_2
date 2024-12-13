package fr.univtours.polytech.tp_note_2.dao;

import fr.univtours.polytech.tp_note_2.model.movieDetails.WsMovieDetailsResult;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;

@Stateless
public class MovieDetailsDaoImpl implements MovieDetailsDao {

    private static String URI = "https://imdb.iamidiotareyoutoo.com/search";

    @Override
    public WsMovieDetailsResult getDetails(String movieTitle) {
        Client client = ClientBuilder.newClient();

        WsMovieDetailsResult result = client.target(URI).queryParam("q", movieTitle).request(MediaType.APPLICATION_JSON)
                .get(WsMovieDetailsResult.class);

        return result;
    }
}
