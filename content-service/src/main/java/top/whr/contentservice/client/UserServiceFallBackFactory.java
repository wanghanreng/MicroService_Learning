package top.whr.contentservice.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import top.whr.contentservice.result.Result;
import top.whr.contentservice.vo.UserVO;

@Component
@Slf4j
public class UserServiceFallBackFactory implements FallbackFactory<UserClient> {
    @Override
    public UserClient create(Throwable cause) {
        log.error("用户服务调用异常:", cause);
        return new UserClient() {
            @Override
            public Result<UserVO> getUser(Integer id) {
                UserVO user = new UserVO();
                user.setId(-1);
                user.setUserName("异常的用户名");
                user.setAvatarUrl("https://oss.hangzhou.com/1.jpg");
                return Result.success(user);
            }
        };
    }
}
