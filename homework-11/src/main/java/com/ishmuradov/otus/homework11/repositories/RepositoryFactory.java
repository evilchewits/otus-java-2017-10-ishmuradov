package com.ishmuradov.otus.homework11.repositories;

public abstract class RepositoryFactory {

  public abstract UserRepository getUserRepository();

  public static RepositoryFactory getRepositoryFactory() {
    return new RepositoryFactoryImpl();
  }

}
