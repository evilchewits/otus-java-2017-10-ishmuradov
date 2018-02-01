package com.ishmuradov.otus.homework11.repositories;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RepositoryFactoryImpl extends RepositoryFactory {
  
  private static final String PERSISTENT_UNIT_NAME = "com.ishmuradov.otus.homework11.jpa";
  private final EntityManagerFactory emf;
  
  public RepositoryFactoryImpl() {
    emf = Persistence.createEntityManagerFactory(PERSISTENT_UNIT_NAME);
  }

  @Override
  public UserRepository getUserRepository() {
    EntityManager em = emf.createEntityManager();
    return new UserRepositoryImpl(em);
  }

}
