//@+leo-ver=5-thin
//@+node:swot.20250912140845.1: * @file service-order/src/main/java/com/tjise/serviceorder/pojo/Item.java
//@@language java
package com.tjise.serviceorder.pojo;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    
    private Long id;
    
    private String title;
    
    private String pic;
    
    private String desc;
    
    private Long price;
}
//@-leo
