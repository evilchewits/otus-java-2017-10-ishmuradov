package com.ishmuradov.otus.homework9;

public interface Repository<T extends Entity> {
  void save(T Entity);
  T load(long id);
}
