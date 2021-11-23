package com.freestack.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Initializer {

    private static final String PERSISTANCE_UNIT_NAME = "myPostGreSqlEntityManager";

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory(PERSISTANCE_UNIT_NAME);
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();


            //your code here



            entityManager.getTransaction().commit();
            entityManager.close();
        } finally {
            entityManagerFactory.close();
        }


    }

}
