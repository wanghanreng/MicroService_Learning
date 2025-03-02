package top.whr.userservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.whr.userservice.entity.User;
import top.whr.userservice.mapper.UserMapper;
import top.whr.userservice.service.UserService;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public User getUserById(Integer id) {
        return this.getById(id);
    }
}
