//@+leo-ver=5-thin
//@+node:swot.20241105083957.1: * @file src/main/java/com/tjise/controller/UploadController.java
//@@language java
//@+doc
// [source,java,linenums]
// ----
//@@c
package com.tjise.controller;

import com.tjise.pojo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class UploadController {
    // 别忘记创建目录: mkdir uploads
    private static final String UPLOAD_DIR = "/Users/swot/swot-learning/java/springboot/spring-boot-ketang/general-manager/uploads/";

    @PostMapping("/upload")
    public Result upload(@RequestParam("image") MultipartFile image) throws IOException {
        // 可以使用 IDEA 的 debug 模式查看变量
        String filename = image.getOriginalFilename();  // 获取上传的文件名
        String[] names = filename.split("\\.");         // 以点切割文件名
        // \\. 中的第一个 \ 是为了让 Java 编译器理解这是一个反斜杠
        // \\. 中的第二个 \ 则是在正则表达式中表示字面量的点
        String extName = names[names.length - 1];       // 获取文件扩展名
        UUID randomString = UUID.randomUUID();          // 生成随机字符串
        String randomFilename  = randomString + "." + extName;
        image.transferTo(new File(UPLOAD_DIR, randomFilename));
        // 前缀路由 uploads 参节点配置内容
        return Result.success("http://localhost:8080/uploads/" + randomFilename);
    }
}
//@+doc
// ----
//@-leo
