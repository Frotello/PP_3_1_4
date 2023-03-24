package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }

    @Override
    public Set<Role> getSetOfRoles(String[] roleNames) {
        Set<Role> roles = new HashSet<>();
        for (String roleName : roleNames) {
            Role role = roleDao.getRoleByName(roleName);
            if (role != null) {
                roles.add(role);
            }
        }
        return roles;
    }

    @Override
    @Transactional
    public void addRole(Role role) {
        roleDao.addRole(role);
    }
}
