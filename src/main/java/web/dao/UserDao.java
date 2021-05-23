package web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import web.model.User;

import java.util.List;

public interface UserDao {

    List<User> allUsers();
    void add(User user);
    void delete(User user);
    void update(User user);
    User getById(Long id);

    User getByName(String name);
}
