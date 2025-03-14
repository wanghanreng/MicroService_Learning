package top.whr.userservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Data
@Configuration
@ConfigurationProperties(prefix = "deepseek.api")
public class DeepSeekConfig {
    private String url;
    private String key;
    private String modelName; // 新增模型名称字段

    @Bean
    public RestTemplate restTemplate2() {
        return new RestTemplate();
    }
}
