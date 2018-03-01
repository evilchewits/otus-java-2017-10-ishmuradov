package com.ishmuradov.otus.homework13.repositories;

public abstract class RepositoryFactory {

  public abstract UserRepository getUserRepository();

  public abstract AddressRepository getAddressRepository();

  public abstract PhoneRepository getPhoneRepository();

  public static RepositoryFactory getRepositoryFactory() {
    return new RepositoryFactoryImpl();
  }

}
