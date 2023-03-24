package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.User;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    @Lazy
    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.addUser(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.updateUser(user);
    }

    @Override
    @Transactional
    public void removeUserById(Long id) {
        userDao.removeUserById(id);
    }

    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.getUserByUserName(username);
    }
}
