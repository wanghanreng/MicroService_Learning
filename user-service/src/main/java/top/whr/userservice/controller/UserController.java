package top.whr.userservice.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user1")
    public String getUser(@RequestParam String username){
        return "User: " + username;
    }
}
