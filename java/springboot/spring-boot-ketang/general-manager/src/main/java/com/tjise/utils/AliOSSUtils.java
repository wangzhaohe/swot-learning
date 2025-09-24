//@+leo-ver=5-thin
//@+node:swot.20250814205415.1: * @file src/main/java/com/tjise/utils/AliOSSUtils.java
//@@language java
//@+doc
// [source,java]
// ----
//@@c
package com.tjise.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// 阿里云 OSS 工具类
// 放入 IOC 容器中，不属于 控制层@Controller、业务层@Service、持久层@Repository，所以就用 @Component 吧
@Component
@Data
public class AliOSSUtils {
    // 这儿的参数是写死的，后面再解决
    private String endpoint = "https://oss-cn-beijing.aliyuncs.com";
    private String accessKeyId = "[REMOVED]";
    private String accessKeySecret = "[REMOVED]";
    private String bucketName = "swot-learn";
    // 实现上传图片到OSS
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
        ossClient.shutdown();  // 关闭 ossClient
        return url;            // 把上传到 oss 的路径返回
    }
}
//@+doc
// ----
//@-leo
