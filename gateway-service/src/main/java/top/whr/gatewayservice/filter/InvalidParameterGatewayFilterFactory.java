package top.whr.gatewayservice.filter;

import com.alibaba.cloud.commons.lang.StringUtils;
import lombok.Data;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class InvalidParameterGatewayFilterFactory extends AbstractGatewayFilterFactory<InvalidParameterGatewayFilterFactory.Config> {

    public static final String NAME = "name";
    public static final String VALUE = "value";

    // 构造方法，初始化配置类
    public InvalidParameterGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        // 返回包含 NAME 和 VALUE 的列表
        return Arrays.asList(NAME, VALUE);
    }

    @Override
    public GatewayFilter apply(Config config) {
        // 返回过滤器函数
        return (exchange, chain) -> {
            // 检查配置中的参数是否有效
            if (StringUtils.isNotBlank(config.getName()) && StringUtils.isNotBlank(config.getValue())) {
                ServerHttpRequest request = exchange.getRequest();
                String value = request.getQueryParams().get(config.getName()).get(0); // 获取请求参数

                // 如果请求参数的值与配置的不匹配，则抛出异常
                if (config.getValue().equalsIgnoreCase(value)) {
                    throw new RuntimeException("非法参数"); // 抛出运行时异常
                }
            }

            return chain.filter(exchange); // 继续处理请求
        };
    }

    // 配置类
    @Data
    public static class Config {
        // 定义用于存储配置的字段
        private String name;
        private String value;
    }
}