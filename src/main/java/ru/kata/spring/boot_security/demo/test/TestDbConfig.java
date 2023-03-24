package ru.kata.spring.boot_security.demo.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class TestDbConfig {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public TestDbConfig(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void dataBaseInit() {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");
        Set<Role> adminSet = new HashSet<>();
        Set<Role> userSet = new HashSet<>();

        roleService.addRole(roleAdmin);
        roleService.addRole(roleUser);

        adminSet.add(roleAdmin);
        adminSet.add(roleUser);

        userSet.add(roleUser);


        User user1 = new User("Vlad", "Parker" ,(byte) 28, "user@mail.ru", "user", "user", userSet);
        User user2 = new User("Polka", "Rich" ,(byte) 28, "admin@mail.ru", "admin", "admin", adminSet);

        userService.addUser(user1);
        userService.addUser(user2);
    }
}
