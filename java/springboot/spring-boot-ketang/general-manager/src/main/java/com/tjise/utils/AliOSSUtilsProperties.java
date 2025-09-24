//@+leo-ver=5-thin
//@+node:swot.20241105152246.2: * @file src/main/java/com/tjise/utils/AliOSSUtilsProperties.java
//@@language java
//@+doc
// 假如还有几个别的类也想使用这 4 个属性该怎么办呢？所以最好再新建一个类用来存储这些需要被共用的配置属性。
// 用 @Component 将类的对象放到控制反转 IOC 容器中。
// [source,java]
// ----
//@@c
package com.tjise.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="aliyun.oss")
@Data
public class AliOSSUtilsProperties {
    // 下面属性值的内容会从配置文件中被自动获取到
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
}
//@+doc
// ----
//@-leo
