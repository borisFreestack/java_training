
package com.freestack.persistence;


import com.freestack.persistence.models.Actor;
import com.freestack.persistence.models.Movie;
import com.freestack.persistence.models.Preview;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import static java.lang.System.out;

public class InitializerRelational {

    public static void tp3() {
        out.println("#### tp3");
        EntityManager entityManager = EntityManagerFactorySingleton
            .getInstance().createEntityManager();
        try {
            entityManager.getTransaction().begin();

            Movie aVeryBigMovie1 = new Movie();
            aVeryBigMovie1.setTitle("A first very big movie");
            aVeryBigMovie1.setLength(75);
            aVeryBigMovie1.setReleaseYear(2012);

            entityManager.persist(aVeryBigMovie1);

            TypedQuery<Movie> query = entityManager.createQuery("select m from Movie m", Movie.class);

            query.getResultList().forEach(movie -> {
                if (Objects.isNull(movie.getDescription()) || movie.getDescription().isBlank()) {
                    movie.setDescription(movie.getTitle());
                }
            });
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    public static void tp4() {
        out.println("#### tp4");
        EntityManager entityManager = EntityManagerFactorySingleton
            .getInstance().createEntityManager();
        try {
            entityManager.getTransaction().begin();

            Preview preview = new Preview();
            preview.setCity("Paris");
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, 12);
            calendar.set(Calendar.MONTH, 11);
            preview.setDate(calendar.getTime());
            preview.setNumberOfSeats(250);

            Movie movie = entityManager.find(Movie.class, 1L);
            movie.addPreview(preview);
            preview.setMovie(movie);

            TypedQuery<Movie> allMoviesQuery = entityManager.createQuery("select m from Movie m", Movie.class);
            List<Movie> allMovies = allMoviesQuery.getResultList();
            out.println("movie with list of previews: ");
            allMovies.forEach(aMovie -> out.println(aMovie));
        } finally {
            entityManager.close();
        }
    }

    public static void tp6() {
        out.println("#### tp6");
        EntityManager entityManager = EntityManagerFactorySingleton
            .getInstance().createEntityManager();
        try {
            entityManager.getTransaction().begin();


            Movie leClanDesSiciliens = new Movie("le clan des siciliens");
            Movie unSingeEnHiver = new Movie("un singe en hiver");
            Actor jeanGabin = new Actor("jean", "gabin");
            Actor linoVentura = new Actor("lino", "ventura");
            jeanGabin.addMovie(leClanDesSiciliens);
            jeanGabin.addMovie(unSingeEnHiver);
            linoVentura.addMovie(leClanDesSiciliens);

            entityManager.persist(leClanDesSiciliens);
            entityManager.persist(unSingeEnHiver);

            entityManager.persist(jeanGabin);
            entityManager.persist(linoVentura);

            entityManager.getTransaction().commit();
            entityManager.clear();

            TypedQuery<Movie> query = entityManager.createQuery("select m from Movie m", Movie.class);
            List<Movie> movies = query.getResultList();
            movies.forEach(aMovie -> {
                out.println(aMovie);
            });
        } finally {
            entityManager.close();
        }
    }

    public static void main(String[] args) {
        tp3();
        tp4();
        tp6();
    }

}

