package com.ishmuradov.otus.homework12.repositories;

import java.sql.SQLException;

import javax.persistence.EntityManagerFactory;

import org.hibernate.cfg.NotYetImplementedException;

import com.ishmuradov.otus.homework12.model.Address;

public class AddressRepositoryImpl implements AddressRepository {

  public AddressRepositoryImpl(EntityManagerFactory emf) {
    throw new NotYetImplementedException();
  }

  @Override
  public Address save(Address entity) throws SQLException {
    throw new NotYetImplementedException();
  }

  @Override
  public Address load(long id) throws SQLException {
    throw new NotYetImplementedException();
  }

  @Override
  public long count() throws SQLException {
    throw new NotYetImplementedException();
  }

}
