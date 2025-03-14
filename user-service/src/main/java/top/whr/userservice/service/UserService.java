package top.whr.userservice.service;

import top.whr.userservice.entity.User;

import java.util.List;

public interface UserService {
    User getUserById(Integer id);
    User createUser(User user);
    User updateUser(User user);
    void deleteUser(Integer id);
    List<User> getAllUsers();
}