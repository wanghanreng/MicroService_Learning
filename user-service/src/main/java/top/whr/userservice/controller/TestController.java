package top.whr.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.whr.userservice.config.OssConfig;

@RestController
@RefreshScope
public class TestController {

    @Value("${whr.username}")
    private String username;

    @Value("${whr.job}")
    private String job;

    @GetMapping("/test")
    public String get() {
        return "我现在用户的名字：" + username + "，职位是：" + job;
    }

    @Autowired
    private OssConfig ossConfig;

    @GetMapping("/test/oss-config")
    public String testOssConfig() {
        return String.format("Endpoint: %s, Access Key Id: %s, Bucket Name: %s",
                ossConfig.getEndpoint(),
                ossConfig.getAccessKeyId(),
                ossConfig.getBucketName());
    }
}