package top.whr.contentservice.handler;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public class SentinelExceptionHandler implements BlockExceptionHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
        String msg = "发生异常"; // 异常信息
        int code = HttpStatus.INTERNAL_SERVER_ERROR.value(); // HTTP 状态码
        log.error("Sentinel 异常: ", e); // 记录错误日志

        if (e instanceof FlowException) {
            msg = "限流异常"; // 处理限流异常
        } else if (e instanceof DegradeException) {
            msg = "降级异常"; // 处理降级异常
        } else if (e instanceof AuthorityException) {
            msg = "权限异常"; // 处理权限异常
        }

        response.setContentType("application/json;charset=utf-8");
        response.setStatus(code);
        response.getWriter().println("{\"msg\":\"" + msg + "\",\"code\":\"" + code + "\"}"); // 返回 JSON 格式的响应
    }
}