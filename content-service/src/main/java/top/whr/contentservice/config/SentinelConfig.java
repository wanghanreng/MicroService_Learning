package top.whr.contentservice.config;


import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.whr.contentservice.handler.SentinelExceptionHandler;
import top.whr.contentservice.handler.SentinelRequestOriginParser;

@Configuration
public class SentinelConfig{
    @Bean
    public BlockExceptionHandler sentinelExceptionHandle() {
        return new SentinelExceptionHandler();
    }

    @Bean
    public RequestOriginParser requestOriginParser() {
        return new SentinelRequestOriginParser();
    }
}