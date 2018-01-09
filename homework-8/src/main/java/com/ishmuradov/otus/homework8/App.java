package com.ishmuradov.otus.homework8;

import java.util.ArrayList;
import java.util.List;

public class App {

  public static void main(String[] args) {

    // Construct an object
    List<String> strList = new ArrayList<String>();
    strList.add("str1");
    strList.add("str2");
    MyBean nestedBean = new MyBean(5, "MyString", new MyBean(), new int[] { 1, 3 },
        new MyBean[] { new MyBean(), new MyBean() }, strList);
    MyBean bean = new MyBean();
    bean.setObjVar(nestedBean);
    
    System.out.println(JsonSerializer.serialize(bean).toString());
  }

}
