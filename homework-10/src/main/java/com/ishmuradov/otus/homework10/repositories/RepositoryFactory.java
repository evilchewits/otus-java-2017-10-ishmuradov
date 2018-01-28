package com.ishmuradov.otus.homework10.repositories;

public abstract class RepositoryFactory {

  public abstract UserRepository getUserRepository();

  public static RepositoryFactory getRepositoryFactory() {
    return new RepositoryFactoryImpl();
  }

}
