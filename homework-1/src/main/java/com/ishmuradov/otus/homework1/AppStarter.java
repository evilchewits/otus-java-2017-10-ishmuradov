package com.ishmuradov.otus.homework1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * This app reads a number from the console input and prints the line with the
 * given number from xanadu.txt file.
 * 
 * Recently read lines are cached, so they will be read from memory if accessing
 * a second time.
 * 
 * @author Ishmuradov
 *
 */
public class AppStarter {
  public static final String FILE_NAME = "xanadu.txt";
  public static final int CACHE_SIZE = 2;

  // configure cache
  private static LoadingCache<Integer, String> linesCache = CacheBuilder.newBuilder().maximumSize(CACHE_SIZE)
      .build(new CacheLoader<Integer, String>() {
        @Override
        public String load(Integer key) throws Exception {
          System.out.println("[Load from disk]");
          try (Stream<String> lines = new BufferedReader(
              new InputStreamReader(getClass().getResourceAsStream("/" + FILE_NAME))).lines()) {
            String line = lines.skip(key).findFirst().get();
            return line;
          }
        }
      });

  private static void run() {
    BufferedReader br = null;

    try {
      br = new BufferedReader(new InputStreamReader(System.in));
      int lineNumber = 0;

      System.out.println("Print \"q\" to exit.");

      // main loop
      while (true) {
        System.out.println("\nCache size: " + getCacheSize());
        System.out.println("Input line number: ");
        String input = br.readLine();

        if ("q".equals(input)) {
          System.out.println("Exit!");
          System.exit(0);
        }

        try {
          lineNumber = Integer.parseInt(input);
        } catch (NumberFormatException e) {
          System.out.println("Invalid line number: " + input);
          continue;
        }

        String line = "";
        try {
          line = linesCache.get(lineNumber);
        } catch (Exception e) {
          System.out.println("Not found!");
          continue;
        }

        System.out.println("Line: " + line);
      }

    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (br != null) {
        try {
          br.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public static long getCacheSize() {
    return linesCache.size();
  }

  public static void main(String[] args) {
    run();
  }
}
