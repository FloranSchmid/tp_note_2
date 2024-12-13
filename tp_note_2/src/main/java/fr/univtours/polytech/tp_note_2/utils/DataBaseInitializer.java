package fr.univtours.polytech.tp_note_2.utils;

import fr.univtours.polytech.tp_note_2.business.MovieBusiness;
import fr.univtours.polytech.tp_note_2.model.MovieBean;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

@Singleton
@Startup
public class DataBaseInitializer {

    @Inject
    private MovieBusiness movieBusiness;

    @PostConstruct
    public void init() {
        MovieBean bean1 = insertBeanInDB("The Lord of the Rings: The Fellowship of the Ring");
        movieBusiness.addMovie(bean1);

        MovieBean bean2 = insertBeanInDB("1917");
        movieBusiness.addMovie(bean2);

        MovieBean bean3 = insertBeanInDB("Joker", 4);
        movieBusiness.addMovie(bean3);
    }

    /**
     * Permet d'insérer un enregistrement en BDD avec le titre et la note.
     * 
     * @param title
     * @param note
     */
    private MovieBean insertBeanInDB(String title, Integer note) {
        MovieBean bean = new MovieBean();

        bean.setTitle(title);
        bean.setNote(note);

        return bean;
    }

    /**
     * Permet d'insérer un enregistrement en BDD avec le titre seulement.
     * 
     * @param title
     */
    private MovieBean insertBeanInDB(String title) {
        MovieBean bean = new MovieBean();

        bean.setTitle(title);

        return bean;
    }
}
