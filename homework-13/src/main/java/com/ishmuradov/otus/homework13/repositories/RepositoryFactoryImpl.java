package com.ishmuradov.otus.homework13.repositories;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RepositoryFactoryImpl extends RepositoryFactory {
  
  private static final String PERSISTENT_UNIT_NAME = "com.ishmuradov.otus.homework13.jpa";
  private final EntityManagerFactory emf;
  
  public RepositoryFactoryImpl() {
    emf = Persistence.createEntityManagerFactory(PERSISTENT_UNIT_NAME);
  }

  @Override
  public UserRepository getUserRepository() {
    return new UserRepositoryImpl(emf);
  }

  @Override
  public AddressRepository getAddressRepository() {
    return new AddressRepositoryImpl(emf);
  }

  @Override
  public PhoneRepository getPhoneRepository() {
    return new PhoneRepositoryImpl(emf);
  }

}
