package top.whr.gatewayservice.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.time.LocalTime;


@Slf4j
@Component
public class TimeAccessGatewayFilterFactory extends AbstractGatewayFilterFactory<TimeAccessGatewayFilterFactory.Config> {

    public TimeAccessGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            LocalTime now = LocalTime.now();
            LocalTime startTime = LocalTime.of(8, 0);  // 8:00
            LocalTime endTime = LocalTime.of(20, 0);   // 20:00

            if (now.isBefore(startTime) || now.isAfter(endTime)) {
                // 打印日志
                log.warn("请求被拒绝：时间段不允许访问，当前时间: {}", now);

                // 设置响应状态码和内容类型
                exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                exchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);

                // 返回错误信息，确保使用 UTF-8 编码
                byte[] bytes = "时间段不允许访问".getBytes(StandardCharsets.UTF_8);
                return exchange.getResponse().writeWith(
                        Mono.just(exchange.getResponse().bufferFactory().wrap(bytes))
                );
            }
            return chain.filter(exchange); // 继续请求
        };
    }

    @Override
    public String name() {
        return "TimeAccess";
    }

    public static class Config {
        // 这里可以添加配置项
    }
}