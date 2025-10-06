//@+leo-ver=5-thin
//@+node:swot.20251006230036.1: * @file service-order/src/test/java/com/tjise/serviceorder/WeatherTest.java
//@+doc
// [source,java]
// ----
//@@c
//@@language java
package com.tjise.serviceorder;

import com.tjise.serviceorder.client.WeatherFeignClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WeatherTest {
    @Autowired
    private WeatherFeignClient weatherFeignClient;

    @Test
    void testGetWeather() {
        String weather = weatherFeignClient.getWeather("101030100");
        System.out.println("weather: "  + weather);
    }
}
//@+doc
// ----
//
//@-leo
