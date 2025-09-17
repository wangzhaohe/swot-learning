//@+leo-ver=5-thin
//@+node:swot.20250916112922.1: * @file service-order/src/main/java/com/tjise/serviceorder/utils/ItemProperties.java
//@+doc
// [source,java]
// ----
//@@c
//@@language java
package com.tjise.serviceorder.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Data
@Component
@ConfigurationProperties(prefix = "myspcloud.item")
public class ItemProperties {
    // 下面属性值的内容会从配置文件中被自动获取到
    private String url;
}
//@+doc
// ----
//
//@-leo
