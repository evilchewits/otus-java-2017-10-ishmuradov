package com.ishmuradov.otus.homework2;

                             /* total size: 32 bytes on x64 JVM */
public class MyClass {       /* object header: 12 bytes on x64 JVM if -XX:+UseCompressedOops (compressing is enabled) */
  private int i = 0;         /* 4 bytes */
  private long l = 1;        /* 8 bytes */
  public boolean b = false;  /* 1 byte */
}                            /* padding: 7 bytes */
