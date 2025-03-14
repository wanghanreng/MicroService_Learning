package top.whr.contentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import top.whr.contentservice.config.SentinelConfig;

@SpringBootApplication
@EnableFeignClients(basePackages = {"top.whr.contentservice.client"})
@Import(SentinelConfig.class)
public class ContentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContentServiceApplication.class, args);
    }

}
