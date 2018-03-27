package com.ishmuradov.otus.homework15.repositories;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.cfg.NotYetImplementedException;

import com.ishmuradov.otus.homework15.model.Phone;

public class PhoneRepositoryImpl implements PhoneRepository {
  
  private EntityManagerFactory emf;

  public PhoneRepositoryImpl(EntityManagerFactory emf) {
    this.emf = emf;
  }

  @Override
  public Phone save(Phone phone) throws SQLException {
    EntityManager em = null;
    try {
      em = emf.createEntityManager();
      em.getTransaction().begin();
      em.persist(phone);
      em.getTransaction().commit();
      return phone;
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  @Override
  public Phone load(long id) throws SQLException {
    EntityManager em = null;
    try {
      em = emf.createEntityManager();
      em.getTransaction().begin();
      Phone phone = em.find(Phone.class, id);
      em.getTransaction().commit();
      return phone;
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  @Override
  public long count() throws SQLException {
    throw new NotYetImplementedException();
  }

}
