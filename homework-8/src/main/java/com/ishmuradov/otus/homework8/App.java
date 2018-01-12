package com.ishmuradov.otus.homework8;

import java.util.ArrayList;
import java.util.List;

public class App {

  public static void main(String[] args) {
    
    // see also unit tests
    
    List<Object> sampleData = new ArrayList<>();
    sampleData.add(new StackOverflowError());
    sampleData.add(new String("test"));
    sampleData.add(new Character('\n'));
    sampleData.add(new Integer(27));
    
    System.out.println(JsonSerializer.serialize(sampleData));

  }

}
