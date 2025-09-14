//@+leo-ver=5-thin
//@+node:swot.20250912104007.1: * @file service-item/src/main/java/com/tjise/serviceitem/pojo/Item.java
//@@language java
package com.tjise.serviceitem.pojo;

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
