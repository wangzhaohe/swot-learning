//@+leo-ver=5-thin
//@+node:swot.20250912141126.1: * @file service-order/src/main/java/com/tjise/serviceorder/pojo/OrderDetail.java
//@@language java
package com.tjise.serviceorder.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
    private String orderId;
    private Item item;
}
//@-leo
