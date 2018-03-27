package com.ishmuradov.otus.homework15.cache;

import java.util.Map;

public interface Cache<K, V> {

  void put(CacheElement<K, V> element);

  CacheElement<K, V> get(K key);

  int getHitCount();

  int getMissCount();
  
  Map<String, String> getCacheParams();

  void dispose();

}