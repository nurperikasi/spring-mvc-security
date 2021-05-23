package web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import web.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleDao extends JpaRepository <Role, Long> {

    Role findByRole(String roleName);

    Role getById(Long id);


}
