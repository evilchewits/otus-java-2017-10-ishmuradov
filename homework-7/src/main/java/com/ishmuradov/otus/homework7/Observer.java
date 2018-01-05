package com.ishmuradov.otus.homework7;

@FunctionalInterface
public interface Observer {
  void notify(Event event);
}
