package test.com.ishmuradov.otus.homework8;

import java.util.List;

public class SampleBean {
  private int intVar;
  private char charVal;
  private Boolean boolVar;
  private String strVar;
  private SampleBean objVar;
  private int[] intArr;
  private SampleBean[] objArr;
  private List<String> strList;

  public SampleBean() {
    super();
  }

  public SampleBean(int intVar, char charVar, Boolean boolVar, String strVar, SampleBean objVar, int[] intArr,
      SampleBean[] objArr, List<String> strList) {
    super();
    this.intVar = intVar;
    this.charVal = charVar;
    this.boolVar = boolVar;
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

  public char getCharVal() {
    return charVal;
  }

  public void setCharVal(char charVal) {
    this.charVal = charVal;
  }

  public Boolean getBoolVar() {
    return boolVar;
  }

  public void setBoolVar(Boolean boolVar) {
    this.boolVar = boolVar;
  }

  public String getStrVar() {
    return strVar;
  }

  public void setStrVar(String strVar) {
    this.strVar = strVar;
  }

  public SampleBean getObjVar() {
    return objVar;
  }

  public void setObjVar(SampleBean objVar) {
    this.objVar = objVar;
  }

  public int[] getIntArr() {
    return intArr;
  }

  public void setIntArr(int[] intArr) {
    this.intArr = intArr;
  }

  public SampleBean[] getObjArr() {
    return objArr;
  }

  public void setObjArr(SampleBean[] objArr) {
    this.objArr = objArr;
  }

  public List<String> getStrList() {
    return strList;
  }

  public void setStrList(List<String> strList) {
    this.strList = strList;
  }

}
