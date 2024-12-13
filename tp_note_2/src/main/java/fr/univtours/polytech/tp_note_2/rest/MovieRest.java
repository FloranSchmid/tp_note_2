package fr.univtours.polytech.tp_note_2.rest;

import jakarta.ws.rs.Path;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import fr.univtours.polytech.tp_note_2.business.MovieBusiness;
import fr.univtours.polytech.tp_note_2.model.MovieBean;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("v1.0")
public class MovieRest {

    @Inject
    private MovieBusiness business;

    @GET
    @Path("movies")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public List<MovieBean> getMovies(@QueryParam("tri") String tri, @QueryParam("title") String filtreTitle,
            @QueryParam("note") Integer filtreNote) {
        
        List<MovieBean> movies = business.getMovies();
        List<MovieBean> result = new ArrayList<MovieBean>();

        if (filtreTitle != null) {
            for (MovieBean movie : movies) {
                if (movie.getTitle().toLowerCase().contains(filtreTitle.toLowerCase())) {
                    result.add(movie);
                }
            }
        } 
        else{
            result = movies;
        }
        if (filtreNote != null) {
            for (MovieBean movie : result) {
                
                if(movie.getNote() != null){
                    if (movie.getNote() != filtreNote) {
                        result.remove(movie);
                    }
                }else{
                    result.remove(movie);
                }
 
            }
        }
        
        if (tri != null) {
            if (tri.equals("croissant")) {
                result.sort(Comparator.comparing(MovieBean::getNote));
            } else if (tri.equals("decroissant")) {
                result.sort(Comparator.comparing(MovieBean::getNote).reversed());
            }
        }

        return result;
    }

    @GET
    @Path("movies/{id}")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public MovieBean getMovie(@PathParam("id") Integer id) {
        MovieBean movieBean = business.getMovie(id);
        return movieBean;
    }




    //Ajouter
    @POST
    @Path("movies")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response createLocation(MovieBean movieBean,
            @HeaderParam(HttpHeaders.AUTHORIZATION) String autorisation) {
        if (autorisation == null) {
            return Response.status(Status.UNAUTHORIZED).build();
        } else if (autorisation.equals("42")) {
            business.addMovie(movieBean);
            
            return Response.ok().build();
        } else {
            return Response.status(Status.FORBIDDEN).build();

        }

    }


    // Mise à jour total du film -> avec méthode appelé lorsqu'on ajoute toutes les
    // informations dans le corps de la
    // requête
    @PUT
    @Path("movies/{id}")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response updateMovie(@PathParam("id") Integer id, MovieBean movieBean,
            @HeaderParam(HttpHeaders.AUTHORIZATION) String autorisation) {


                
        if (autorisation == null) {
            return Response.status(Status.UNAUTHORIZED).build();
        } 
        else if (autorisation.equals("42")) {
            if (getMovie(id) != null) {
                movieBean.setId(id);
                business.updateMovie(movieBean);
                return Response.ok().build();

            } 
            else {
                return Response.status(Status.NOT_FOUND).build();
            }
        }
        return Response.status(Status.FORBIDDEN).build();
    }

    @PATCH
    @Path("movies/{id}")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response updatePartielleLocation(@PathParam("id") Integer id, MovieBean movieBean,
            @HeaderParam(HttpHeaders.AUTHORIZATION) String autorisation) {

        if (autorisation == null) {
            return Response.status(Status.UNAUTHORIZED).build();
        } else if (autorisation.equals("42")) {
            if (getMovie(id) != null) {
                movieBean.setId(id);

                if (movieBean.getTitle() == null) {
                    movieBean.setTitle(business.getMovie(id).getTitle());
                }
                if (movieBean.getNote() == null) {
                    movieBean.setNote(business.getMovie(id).getNote());
                }

                business.updateMovie(movieBean);
                return Response.ok().build();
            }
            return Response.status(Status.NOT_FOUND).build();
        } else {
            return Response.status(Status.FORBIDDEN).build();

        }

    }

    @DELETE
    @Path("movies/{id}")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response deleteLocation(@PathParam("id") Integer id,
            @HeaderParam(HttpHeaders.AUTHORIZATION) String autorisation) {
        if (autorisation == null) {
            return Response.status(Status.UNAUTHORIZED).build();
        } else if (autorisation.equals("42")) {
            try {
                business.deleteMovie(id);

            } catch (Exception e) {
                return Response.status(Status.NOT_FOUND).build();
            }
            return Response.ok().build();

        } else {
            return Response.status(Status.FORBIDDEN).build();

        }
    }

}
