//@+leo-ver=5-thin
//@+node:swot.20250912140908.1: * @file service-order/src/main/java/com/tjise/serviceorder/pojo/Order.java
//@@language java
package com.tjise.serviceorder.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private String orderId;

    private Long userId;

    private Date createDate;

    private Date updateDate;

    private List<OrderDetail> orderDetails;
}
//@-leo
