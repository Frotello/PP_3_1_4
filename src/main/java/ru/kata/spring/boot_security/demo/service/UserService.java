package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    List<User> findAllUsers ();

    User getUserById(long id);

    void saveUser(User user);

    void deleteUserById(long id);

    User findByUsername(String username);

    void updateUser(User user);

    User passwordCoder(User user);
}
