package com.freestack.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

public class EntityManagerFactorySingleton {
     private static final String PERSISTANCE_UNIT_NAME = "myPostGreSqlEntityManager";

     private static EntityManagerFactory emf;

     private EntityManagerFactorySingleton(){}

     public static EntityManagerFactory getInstance() {
          if(emf == null) emf = Persistence.createEntityManagerFactory(PERSISTANCE_UNIT_NAME);
          return emf;
     }


     @Override
     protected void finalize() throws Throwable {
          emf.close();
     }
}
