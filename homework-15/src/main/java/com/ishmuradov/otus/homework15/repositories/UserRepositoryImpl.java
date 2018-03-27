package com.ishmuradov.otus.homework15.repositories;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.ishmuradov.otus.homework15.model.User;

public class UserRepositoryImpl implements UserRepository {

  private EntityManagerFactory emf;
  private long saveCount = 0;
  private long loadCount = 0;

  public UserRepositoryImpl(EntityManagerFactory emf) {
    this.emf = emf;
  }
  
  @Override
  public User save(User user) throws SQLException {
    EntityManager em = null;
    try {
      em = emf.createEntityManager();
      em.getTransaction().begin();
      em.persist(user);
      em.getTransaction().commit();
      saveCount++;
      return user;
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }
  
  @Override
  public User load(long id) throws SQLException {
    EntityManager em = null;
    try {
      em = emf.createEntityManager();
      em.getTransaction().begin();
      User user = em.find(User.class, id);
      em.getTransaction().commit();
      loadCount++;
      return user;
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  @Override
  public long count() throws SQLException {
    EntityManager em = null;
    try {
      em = emf.createEntityManager();
      CriteriaBuilder qb = em.getCriteriaBuilder();
      CriteriaQuery<Long> cq = qb.createQuery(Long.class);
      cq.select(qb.count(cq.from(User.class)));
      return em.createQuery(cq).getSingleResult();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }
  
  public long getSaveCount() {
    return saveCount;
  }

  public long getLoadCount() {
    return loadCount;
  }

}
