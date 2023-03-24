package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @GetMapping(value = "login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/admin")
    public String showAdminPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("users", userService.listUsers());
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getAllRoles());
        return "adminPage";
    }

    @GetMapping("/add")
    public String newUserForm(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getAllRoles());
        return "newUser";
    }

    @PostMapping("/new")
    public String createNewUser(@ModelAttribute("user") User user
            , @RequestParam(value = "roles") String[] roles) {
        user.setRoles(roleService.getSetOfRoles(roles));
        userService.addUser(user);
        return "redirect:/admin";
    }

    @PatchMapping("/{id}/update")
    public String update(@ModelAttribute("user") User user
            , @RequestParam(value = "roles") String[] roles) {
        user.setRoles(roleService.getSetOfRoles(roles));
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") long id) {
        userService.removeUserById(id);
        return "redirect:/admin";
    }
}
