//@+leo-ver=5-thin
//@+node:swot.20250924105814.1: * @file src/main/java/com/tjise/utils/AliOSSUtils.java
//@@language java
//@+doc
// [source,java,linenums]
// ----
//@@c
package com.tjise.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 阿里云 OSS 工具类
 */
 
// 放入IOC 容器中，不属于 控制层@Controller、业务层@Service、持久层@Repository，所以就用 @Component 吧
@Component
public class AliOSSUtils {
    // 这儿放置 @Value 取值
    //@+others
    //@+node:swot.20250924105814.2: ** 原来下面 4 个参数值是写死的，现在使用 @Value 读取配置
    // @Value 注解通常用于外部配置的属性注入，具体用法为： @Value("${配置文件中的key}")
    @Value("${aliyun.oss.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyun.oss.bucketName}")
    private String bucketName;
    //@-others
    /**
     * 实现上传图片到OSS
     */
    public String upload(MultipartFile multipartFile) throws IOException {
        // 获取上传的文件的输入流
        InputStream inputStream = multipartFile.getInputStream();

        // 避免文件覆盖
        String fileName = LocalDateTime.now().format(
            DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss")) + multipartFile.getOriginalFilename();

        //上传文件到 OSS（没有使用 region，还是老的方式，建议使用新方式 create，不使用 build）
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucketName, fileName, inputStream);

        //文件访问路径
        String url = endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + fileName;
        // 关闭ossClient
        ossClient.shutdown();
        return url;// 把上传到oss的路径返回
    }
}
//@+doc
// ----
//@-leo
