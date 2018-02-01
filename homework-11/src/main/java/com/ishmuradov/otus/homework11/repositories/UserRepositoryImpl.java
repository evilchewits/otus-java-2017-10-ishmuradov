package com.ishmuradov.otus.homework11.repositories;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.ishmuradov.otus.homework11.model.User;

public class UserRepositoryImpl implements UserRepository {

  private EntityManager em;

  public UserRepositoryImpl(EntityManager em) {
    this.em = em;
  }

  public void save(User user) throws SQLException {
    try {
      em.getTransaction().begin();
      em.persist(user);
      em.getTransaction().commit();
    } finally {
      if (!em.isOpen()) {
        em.close();
      }
    }
  }

  public User load(long id) throws SQLException {
    try {
      em.getTransaction().begin();
      User user = em.find(User.class, id);
      em.getTransaction().commit();
      return user;
    } finally {
      if (!em.isOpen()) {
        em.close();
      }
    }
  }

  public long count() throws SQLException {
    try {
      CriteriaBuilder qb = em.getCriteriaBuilder();
      CriteriaQuery<Long> cq = qb.createQuery(Long.class);
      cq.select(qb.count(cq.from(User.class)));
      return em.createQuery(cq).getSingleResult();
    } finally {
      if (!em.isOpen()) {
        em.close();
      }
    }
  }

}
