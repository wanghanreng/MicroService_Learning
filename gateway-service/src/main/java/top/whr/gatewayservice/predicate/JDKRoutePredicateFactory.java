package top.whr.gatewayservice.predicate;

import com.alibaba.cloud.commons.lang.StringUtils;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

@Component
public class JDKRoutePredicateFactory extends AbstractRoutePredicateFactory<JDKRoutePredicateFactory.Config> {

    public static final String VERSION = "version"; // 定义常量 VERSION

    // 构造方法，初始化配置类
    public JDKRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        // 返回包含 VERSION 的单个列表
        return Collections.singletonList(VERSION);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return new GatewayPredicate() { // 返回新的 GatewayPredicate

            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                // 获取当前 JDK 版本
                String version = System.getProperty("java.version");
                // 检查 version 是否非空并且以 config.getVersion() 开头
                return StringUtils.isNotBlank(version) && version.startsWith(config.getVersion());
            }
            @Override
            public Object getConfig() { return config; }

            @Override
            public String toString(){ return String.format("JDK Version: %s",config.getVersion()); }
        };
    }

    // 配置类
    @Data
    @NoArgsConstructor
    public static class Config {
        @NotNull // 确保 version 字段不为空
        private String version; // JDK 版本字段
    }
}
