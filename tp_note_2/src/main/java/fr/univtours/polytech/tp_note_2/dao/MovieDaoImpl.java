package fr.univtours.polytech.tp_note_2.dao;

import java.util.List;

import fr.univtours.polytech.tp_note_2.model.MovieBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class MovieDaoImpl implements MovieDao {

    @PersistenceContext(unitName = "TpNote2")
    private EntityManager em;

    @Override
    public void createMovie(MovieBean bean) {
        em.persist(bean);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<MovieBean> getMovies() {
        Query request = em.createQuery("select l from MovieBean l");
        return request.getResultList();
    }

    @Override
    public MovieBean getMovie(Integer id) {
        return em.find(MovieBean.class, id);
    }

    @Override
    public void updateMovie(MovieBean movieBean) {
        em.merge(movieBean);
    }

    @Override
    public void deleteMovie(MovieBean movieBean) {
        em.remove(movieBean);
    }
}
