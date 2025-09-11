//@+leo-ver=5-thin
//@+node:swot.20241015083350.22: * @file spring-boot-ketang/spring-boot-request-response/src/main/java/com/tjise/pojo/User.java
//@@language java
//@+doc
// 改造成包含三个属性 name & age & address，为了演示复杂实体对象参数的获取。
//
// [source,java,linenums]
// ----
//@@c
package com.tjise.pojo;

public class User {
    private String name;
    private int age;
    private Address address;

    public User() {
    }

    public User(String name, int age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Address getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address=" + address +
                '}';
    }
}
//@+doc
// ----
//@-leo
