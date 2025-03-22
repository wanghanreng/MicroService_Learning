package top.whr.gatewayservice.filter;

import com.alibaba.cloud.commons.lang.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class AuthorizationFilter implements GlobalFilter, Ordered { // 实现 GlobalFilter 和 Ordered 接口

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 从请求参数中获取 token
        String token = exchange.getRequest().getQueryParams().getFirst("token");

        // 检查 token 是否为空或空白
        if (StringUtils.isBlank(token)) {
            log.error("网关请求认证失败"); // 记录错误日志
            throw new RuntimeException("非法请求"); // 抛出异常
        }

        // 调用链中的下一个过滤器
        return chain.filter(exchange);
    }

    /**
     * 获取过滤器的顺序。
     * @return 表示优先级，数值越小优先级越高。
     */
    @Override
    public int getOrder() {
        return 0; // 返回优先级为 0
    }
}