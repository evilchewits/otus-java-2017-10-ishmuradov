package com.ishmuradov.otus.homework12.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@javax.persistence.Entity
@Table(name = "USER")
public class User extends Entity {

  @Column(name = "NAME")
  private String name;

  @Column(name = "AGE")
  private int age;

  @OneToOne(cascade = CascadeType.ALL)
  private Address address;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Phone> phones;

  public User() {
    super();
  }

  public User(String name, int age, Address address) {
    super();
    this.name = name;
    this.age = age;
    this.address = address;
  }
  
  public User(String name, int age, Address address, List<Phone> phones) {
    super();
    this.name = name;
    this.age = age;
    this.address = address;
    if (phones != null) {
      for (Phone phone : phones) {
        phone.setUser(this);
      }
    }
    this.phones = phones;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public List<Phone> getPhones() {
    return phones;
  }

  public void setPhones(List<Phone> phones) {
    if (phones != null) {
      for (Phone phone : phones) {
        phone.setUser(this);
      }
    }
    this.phones = phones;
  }

  @Override
  public String toString() {
    return "User [name=" + name + ", age=" + age + ", address=" + address + ", phones=" + phones + "]";
  }

}
