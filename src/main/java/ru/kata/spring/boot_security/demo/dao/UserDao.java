package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);

    User getUserById(Long id);

    User getUserByUserName(String userName);

    void updateUser(User user);

    void removeUserById(Long id);

    List<User> listUsers();
}
