package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("SELECT r FROM Role r", Role.class).getResultList();
    }

    @Override
    public Role getRoleByName(String name) {
        return entityManager.createQuery(
                        "SELECT r from Role r where r.role=:role", Role.class)
                .setParameter("role", name).getSingleResult();
    }

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }
}
