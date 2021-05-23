package web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import web.model.Role;
import web.model.User;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String firstname);

    User findUserById(Long id);
}
