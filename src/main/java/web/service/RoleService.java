package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.Role;
import web.dao.RoleDao;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public Set<Role> getRoleByName(List<String> listStringRoles){
        return   listStringRoles.stream()
                .map(s -> roleDao.findByRole(s))
                .collect(Collectors.toSet());
    }
}
