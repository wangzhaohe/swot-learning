//@+leo-ver=5-thin
//@+node:swot.20241015083350.23: * @file spring-boot-ketang/spring-boot-request-response/src/main/java/com/tjise/pojo/Address.java
//@@language java
//@+doc
// .会被 User.java 使用
// [source,java,linenums]
// ----
//@@c
package com.tjise.pojo;

public class Address {
    private String province;
    private String city;

    public Address() {
    }

    public Address(String city, String province) {
        this.city = city;
        this.province = province;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "province='" + province + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
//@+doc
// ----
//@-leo
