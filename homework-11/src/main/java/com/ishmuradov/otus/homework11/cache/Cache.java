package com.ishmuradov.otus.homework11.cache;

public interface Cache<K, V> {

  void put(CacheElement<K, V> element);

  CacheElement<K, V> get(K key);

  int getHitCount();

  int getMissCount();

  void dispose();

}