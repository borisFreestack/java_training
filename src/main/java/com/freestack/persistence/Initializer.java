package com.freestack.persistence;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

public class Initializer {

    private final static String PERSISTANCE_UNIT_NAME = "myPostGreSqlEntityManager";

    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory(PERSISTANCE_UNIT_NAME);

    }

}
