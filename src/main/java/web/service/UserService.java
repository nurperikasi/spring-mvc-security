package web.service;

import web.model.Role;
import web.model.User;

import java.util.List;

public interface UserService {

    List<Role> getAllRoles();

    List<User> allUsers();

    void add(User user, String[] roleList);

    void delete(User user);

    void update(User user, String[] roleList);

    User getById(Long id);

    User getByName(String name);
}
