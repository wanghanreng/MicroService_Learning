package whr.redisservice.service.Impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import whr.redisservice.config.CloopenConfig;
import whr.redisservice.config.RedisCache;
import whr.redisservice.config.RedisKeys;
import whr.redisservice.service.SmsService;
import whr.redisservice.utils.CommonUtils;

@Service
@AllArgsConstructor
@Slf4j
public class SmsServiceImpl implements SmsService {
    private final CloopenConfig cloopenConfig;
    private final RedisCache redisCache;

    @Override
    public void sendSms(String phone) {
        int code = CommonUtils.generateCode();
        redisCache.set(RedisKeys.getSmsKey(phone), code, 60); // 缓存验证码，过期时间60秒

        boolean result = send(phone, code);
        if (result) {
            log.info("短信发送成功，手机号是：{}", phone);
        } else {
            log.error("短信发送失败，手机号是：{}", phone);
        }
    }

    private boolean send(String phone, int code) {
        // 短信发送逻辑...
        try {
            log.info("发送短信，手机号是：{}，验证码是：{}", phone, code);
            // 这里是调用短信 SDK 发送短信的代码，简化为日志记录
            return true; // 假设发送成功
        } catch (Exception e) {
            log.error("短信发送异常", e);
            return false;
        }
    }
}