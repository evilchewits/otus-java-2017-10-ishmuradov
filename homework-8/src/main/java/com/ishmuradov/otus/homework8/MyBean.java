package com.ishmuradov.otus.homework8;

import java.util.List;

public class MyBean {
  private int intVar;
  private String strVar;
  private MyBean objVar;
  private int[] intArr;
  private MyBean[] objArr;
  private List<String> strList;

  public MyBean() {
    super();
  }

  public MyBean(int intVar, String strVar, MyBean objVar, int[] intArr, MyBean[] objArr, List<String> strList) {
    super();
    this.intVar = intVar;
    this.strVar = strVar;
    this.objVar = objVar;
    this.intArr = intArr;
    this.objArr = objArr;
    this.strList = strList;
  }

  public int getIntVar() {
    return intVar;
  }

  public void setIntVar(int intVar) {
    this.intVar = intVar;
  }

  public String getStrVar() {
    return strVar;
  }

  public void setStrVar(String strVar) {
    this.strVar = strVar;
  }

  public MyBean getObjVar() {
    return objVar;
  }

  public void setObjVar(MyBean objVar) {
    this.objVar = objVar;
  }

  public int[] getIntArr() {
    return intArr;
  }

  public void setIntArr(int[] intArr) {
    this.intArr = intArr;
  }

  public MyBean[] getObjArr() {
    return objArr;
  }

  public void setObjArr(MyBean[] objArr) {
    this.objArr = objArr;
  }

  public List<String> getStrList() {
    return strList;
  }

  public void setStrList(List<String> strList) {
    this.strList = strList;
  }

}
