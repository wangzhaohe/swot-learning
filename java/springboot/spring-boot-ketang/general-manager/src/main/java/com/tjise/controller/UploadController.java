//@+leo-ver=5-thin
//@+node:swot.20241105085808.1: * @file src/main/java/com/tjise/controller/UploadController.java
//@@language java
//@+doc
// [source,java,linenums]
// ----
//@@c
package com.tjise.controller;

import com.tjise.pojo.Result;
import com.tjise.utils.AliOSSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
public class UploadController {

    // DI 注入阿里云 OSS AliOSSUtils 的对象
    @Autowired
    private AliOSSUtils aliOSSUtils;

    @PostMapping("/upload")
    public Result upload(@RequestParam("image") MultipartFile image) throws IOException {
        // 把图片直接上传到阿里云 OSS 服务中
        String url = aliOSSUtils.upload(image);
        // 返回 url 地址给前端
        return Result.success(url);
    }
}
//@+doc
// ----
//@-leo
