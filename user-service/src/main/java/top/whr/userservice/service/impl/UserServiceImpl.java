package top.whr.userservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.whr.userservice.entity.User;
import top.whr.userservice.mapper.UserMapper;
import top.whr.userservice.service.UserService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User getUserById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public User createUser(User user) {
        LocalDateTime now = LocalDateTime.now();
        user.setCreateTime(now); // 设置当前时间
        user.setUpdateTime(now); // 设置当前时间
        userMapper.insert(user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        LocalDateTime now = LocalDateTime.now();
        user.setUpdateTime(now); // 设置更新时间
        userMapper.updateById(user);
        return user;
    }

    @Override
    public void deleteUser(Integer id) {
        userMapper.deleteById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.selectList(null); // 查询所有用户
    }
}
