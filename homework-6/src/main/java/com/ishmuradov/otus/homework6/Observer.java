package com.ishmuradov.otus.homework6;

@FunctionalInterface
public interface Observer {
  void notify(Event event);
}
