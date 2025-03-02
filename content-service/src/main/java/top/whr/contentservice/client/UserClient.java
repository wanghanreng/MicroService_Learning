package top.whr.contentservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.whr.contentservice.vo.UserVO;

@FeignClient(name = "user-service")
public interface UserClient {
    @GetMapping("/user/{id}")
    UserVO getUser(@PathVariable("id") Integer id);
}
