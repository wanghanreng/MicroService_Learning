package top.whr.userservice.controller;


import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import top.whr.userservice.entity.User;
import top.whr.userservice.result.Result;
import top.whr.userservice.service.UserService;

@RestController
@Slf4j
@RefreshScope
public class UserController {

//    @Resource
//    private RestTemplate restTemplate;
//
    @Resource
    private UserService userService;

    @Value("${serviceFlag}")
    private Boolean serviceFlag;
//
//    @GetMapping("/user")
//    public String getUser(@RequestParam String username) {
//        return "User: " + username;
//    }
//
//    @GetMapping("/greeting")
//    public String getGreeting(@RequestParam String name) {
//        String nodeServiceUrl = "http://localhost:3000/greeting?name=" + name;
//        String info = restTemplate.getForObject(nodeServiceUrl, String.class);
//        return "调用 Node.js 结果: " + info;
//    }
//
//    @GetMapping("/hello")
//    public String getHello(@RequestParam String name) {
//        String pyServiceUrl = "http://localhost:3000/hello?name=" + name;
//        String info = restTemplate.getForObject(pyServiceUrl, String.class);
//        return "调用 flask 结果: " + info;
//    }
//
//    @GetMapping("/ai")
//    public String getAnswer(@RequestParam String question) {
//        String aiServiceUrl = "http://localhost:8804/question?question=" + question;
//        return restTemplate.getForObject(aiServiceUrl, String.class);
//    }

    @GetMapping("/user/{id}")
    public Result<User> getUser(@PathVariable Integer id) {
        log.info("请求用户信息");
        if (serviceFlag) {
            User user = userService.getUserById(id);
            if (user != null) {
                return Result.success(user);
            } else {
                return Result.failure("用户不存在", 404);
            }
        } else {
            return Result.failure("用户服务正在维护，请稍后再试。", 503);
        }
    }
}