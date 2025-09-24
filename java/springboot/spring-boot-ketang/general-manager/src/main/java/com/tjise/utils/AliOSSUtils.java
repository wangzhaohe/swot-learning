//@+leo-ver=5-thin
//@+node:swot.20250815195524.1: * @file src/main/java/com/tjise/utils/AliOSSUtils.java
//@@language java
//@+doc
// [source,java]
// ----
//@@c
package com.tjise.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// 阿里云 OSS 工具类
// 放入IOC 容器中，不属于 控制层 @Controller、业务层 @Service、持久层 @Repository，所以就用 @Component 吧
// @ConfigurationProperties(prefix = "aliyun.oss")
@Component
@Data  // 内部需要使用 setter 来设置属性值，所以要使用 lombok 来自动生成 setter
public class AliOSSUtils {

    @Autowired
    private AliOSSUtilsProperties aliOSSUtilsProperties;

    // 实现上传图片到OSS
    public String upload(MultipartFile multipartFile) throws IOException {
        // 获取上传的文件的输入流
        InputStream inputStream = multipartFile.getInputStream();

        // 避免文件覆盖
        String fileName = LocalDateTime.now().format(
            DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss")) + multipartFile.getOriginalFilename();

        //上传文件到 OSS
        OSS ossClient = new OSSClientBuilder().build(
            aliOSSUtilsProperties.getEndpoint(),
            aliOSSUtilsProperties.getAccessKeyId(),
            aliOSSUtilsProperties.getAccessKeySecret()
        );
        ossClient.putObject(
            aliOSSUtilsProperties.getBucketName(),
            fileName,
            inputStream
        );

        //文件访问路径
        String url = aliOSSUtilsProperties.getEndpoint().split("//")[0] + "//" +
                     aliOSSUtilsProperties.getBucketName() + "." +
                     aliOSSUtilsProperties.getEndpoint().split("//")[1] + "/" + fileName;
        ossClient.shutdown();  // 关闭 ossClient
        return url;            // 把上传到 oss 的路径返回
    }
}
//@+doc
// ----
//@-leo
