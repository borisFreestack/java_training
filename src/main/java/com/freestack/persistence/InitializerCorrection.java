package com.freestack.persistence;


import com.freestack.persistence.models.Movie;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

import static java.lang.System.out;

public class InitializerCorrection {

    public static void tp2question1() {
        out.println("#### tp2question1");
        EntityManager entityManager = EntityManagerFactorySingleton
            .getInstance().createEntityManager();
        try {
            entityManager.getTransaction().begin();

            Movie aVeryBigMovie1 = new Movie();
            aVeryBigMovie1.setTitle("A first very big movie");
            aVeryBigMovie1.setDescription("A very big movie for big smart people");
            aVeryBigMovie1.setLength(75);
            aVeryBigMovie1.setReleaseYear(2012);

            Movie aVeryBigMovie2 = new Movie();
            aVeryBigMovie2.setTitle("Another very big movie 2");
            aVeryBigMovie2.setDescription("A very bigger movie for even bigger & smarter people ");
            aVeryBigMovie2.setLength(93);
            aVeryBigMovie2.setReleaseYear(2013);

            entityManager.persist(aVeryBigMovie1);
            entityManager.persist(aVeryBigMovie2);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    public static void tp2question2() {
        out.println("#### tp2question2");
        EntityManager entityManager = EntityManagerFactorySingleton
            .getInstance().createEntityManager();
        try {
            Query queryAllMovies = entityManager.createQuery("select m from Movie m");

            List<Movie> movies = queryAllMovies.getResultList();
            out.println("list of movies");
            movies.forEach(movie -> {
                out.println(movie);
            });
        } finally {
            entityManager.close();
        }
    }

    public static void tp2question3() {
        out.println("#### tp2question3");
        EntityManager entityManager = EntityManagerFactorySingleton
            .getInstance().createEntityManager();
        try {
            Query queryOneMovie = entityManager.createQuery("select m from Movie m where m.id = :idToSearch");
            queryOneMovie.setParameter("idToSearch", 1L);
            Movie movie = (Movie) queryOneMovie.getSingleResult();
            if (movie != null) {
                out.println("movie with id: " + 1L + " is : " + movie);
            }
        } finally {
            entityManager.close();
        }
    }

    public static void tp2question3bis() {
        out.println("#### tp2question3bis");
        EntityManager entityManager = EntityManagerFactorySingleton
            .getInstance().createEntityManager();
        try {
            TypedQuery<Movie> queryOneMovie = entityManager.createQuery("select m from Movie m where m.id = :idToSearch", Movie.class);
            queryOneMovie.setParameter("idToSearch", 1L);
            Movie movie = queryOneMovie.getSingleResult();
            if (movie != null) {
                out.println("movie with id: " + 1L + " is : " + movie);
            }
        } finally {
            entityManager.close();
        }
    }

    public static void tp2question3ter() {
        out.println("#### tp2question3ter");
        EntityManager entityManager = EntityManagerFactorySingleton
            .getInstance().createEntityManager();
        try {
            Movie movie = entityManager.find(Movie.class, 1L);
            if (movie != null) {
                out.println("movie with id: " + 1L + " is : " + movie);
            }
        } finally {
            entityManager.close();
        }
    }

    public static void tp2question4() {
        out.println("#### tp2question4");
        EntityManager entityManager = EntityManagerFactorySingleton
            .getInstance().createEntityManager();
        try {
            Query allTitleAndDescQuery = entityManager.createQuery("select m.title, m.description from Movie m");
            List<Object[]> titleAndDescList = allTitleAndDescQuery.getResultList();
            out.println("title and descriptions of all movies are :");
            titleAndDescList.forEach(titleAndDesc -> out.println("title: " + titleAndDesc[0] + ", description: " + titleAndDesc[1]));
        } finally {
            entityManager.close();
        }
    }

    public static void tp2question5() {
        out.println("#### tp2question5");
        EntityManager entityManager = EntityManagerFactorySingleton
            .getInstance().createEntityManager();
        try {
            Query maxLengthQuery = entityManager.createQuery("select MAX(m.length) from Movie m");
            Integer maxLength = (Integer) maxLengthQuery.getSingleResult();
            out.println("max length is : " + maxLength);

            Query filmHavingMaxLengthQuery = entityManager.createQuery("select m from Movie m where m.length = :lengthSearched");
            filmHavingMaxLengthQuery.setParameter("lengthSearched", maxLength);
            List moviesHavingMaxLength = filmHavingMaxLengthQuery.getResultList();
            out.println("movies having max length are :");
            moviesHavingMaxLength.forEach(movie -> out.println(movie));
        } finally {
            entityManager.close();
        }
    }

    public static void tp2question6() {
        out.println("#### tp2question6");
        EntityManager entityManager = EntityManagerFactorySingleton
            .getInstance().createEntityManager();
        try {
            Query meanLengthQuery = entityManager.createQuery("select AVG (m.length) from Movie m");
            Double meanLength = (Double) meanLengthQuery.getSingleResult();
            out.println("mean length is : " + meanLength);
        } finally {
            entityManager.close();
        }
    }

    public static void tp2question7() {
        out.println("#### tp2question7");
        String textSearched = "first";
        EntityManager entityManager = EntityManagerFactorySingleton
            .getInstance().createEntityManager();
        try {

            TypedQuery<Movie> moviesHavingTextLikeQuery = entityManager.createQuery("select m from Movie m where " +
                "m.title like :text", Movie.class);
            moviesHavingTextLikeQuery.setParameter("text", "%" + textSearched + "%");
            List moviesHavingMaxLength = moviesHavingTextLikeQuery.getResultList();
            out.println("movies having " + textSearched + " in title are :");
            moviesHavingMaxLength.forEach(movie -> out.println(movie));
        } finally {
            entityManager.close();
        }
    }

    public static void tp2question8() {
        out.println("#### tp2question8");
        String textSearched = "bigger";
        EntityManager entityManager = EntityManagerFactorySingleton
            .getInstance().createEntityManager();
        try {

            TypedQuery<Movie> moviesHavingTextLikeQuery = entityManager.createQuery("select m from Movie m where " +
                "m.title like :text " +
                "or m.description like :text", Movie.class);
            moviesHavingTextLikeQuery.setParameter("text", "%" + textSearched + "%");
            List moviesHavingMaxLength = moviesHavingTextLikeQuery.getResultList();
            out.println("movies having " + textSearched + " in title or desc are :");
            moviesHavingMaxLength.forEach(movie -> out.println(movie));
        } finally {
            entityManager.close();
        }
    }

    public static void tp2question8bis() {
        out.println("#### tp2question8bis");
        String textSearched = "bigger";
        EntityManager entityManager = EntityManagerFactorySingleton
            .getInstance().createEntityManager();
        try {

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

            CriteriaQuery<Movie> criteriaQuery = criteriaBuilder.createQuery(Movie.class);
            Root movie = criteriaQuery.from(Movie.class);
            Predicate haveTitleWordInside = criteriaBuilder.like(movie.get("title"), "%"+textSearched+"%");
            Predicate haveDescWordInside = criteriaBuilder.like(movie.get("description"), "%"+textSearched+"%");
            Predicate haveTitleOrDescWordInside = criteriaBuilder.or(haveTitleWordInside, haveDescWordInside);
            criteriaQuery = criteriaQuery.where(haveTitleOrDescWordInside);
            Query moviesHavingTextLikeQuery = entityManager.createQuery(criteriaQuery);
            List<Movie> moviesHavingTextLike = moviesHavingTextLikeQuery.getResultList();
            out.println("movies having " + textSearched + " in title or desc are :");
            moviesHavingTextLike.forEach(aMovie -> out.println(aMovie));
        } finally {
            entityManager.close();
        }
    }


    public static void main(String[] args) {
        tp2question1();
        tp2question2();
        tp2question3();
        tp2question3bis();
        tp2question3ter();
        tp2question4();
        tp2question5();
        tp2question6();
        tp2question7();
        tp2question8();
        tp2question8bis();
    }

}
