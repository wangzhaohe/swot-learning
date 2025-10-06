//@+leo-ver=5-thin
//@+node:swot.20251006224019.1: * @file service-order/src/main/java/com/tjise/serviceorder/client/WeatherFeignClient.java
//@+doc
// [source,java]
// ----
//@@c
//@@language java
package com.tjise.serviceorder.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// weather-client 是自己随便起的名字，在这儿没具体作用
// url 是第三方 API 的域名
@FeignClient(name = "weather-client", url = "http://t.weather.sojson.com")
public interface WeatherFeignClient {
    @GetMapping("/api/weather/city/{citycode}")
    String getWeather(@PathVariable("citycode") Long citycode);
}
//@+doc
// ----
//@-leo
