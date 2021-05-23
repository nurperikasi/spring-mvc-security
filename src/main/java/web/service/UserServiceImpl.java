package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.Role;
import web.model.User;
import web.dao.RoleDao;
import web.dao.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService{

    private final BCryptPasswordEncoder passwordEncoder;

    private final RoleDao roleDao;

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao, BCryptPasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Role> getAllRoles() {
        List<Role> roles = new ArrayList<>();
        roles.add(roleDao.getById(1L));
        roles.add(roleDao.getById(2L));
        return  roles;
    }
    @Override
    public List<User> allUsers() {
        return userDao.allUsers();
    }

    @Override
    public void add(User user, String[] roleList) {
        List<Role> set = new ArrayList<>();
        if (roleList != null){
            for (int i =0; i<roleList.length; i++) {
                if (!user.getRoles().contains(roleList[i])){
                    set.add(roleDao.findByRole(roleList[i]));
                }
            }
            user.setRoles(set);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(user);
        userDao.add(user);
    }

    @Override
    public void delete(User user) {
        List set = new ArrayList();
        user.setRoles(set);
        userDao.delete(user);
    }

    @Override
    public void update(User user, String[] roleList) {
        List<Role> set = new ArrayList<>();
        if (roleList != null){
            for (int i =0; i<roleList.length; i++) {
                if (!user.getRoles().contains(roleList[i])){
                    set.add(roleDao.findByRole(roleList[i]));
                    System.out.println(roleDao.findByRole(roleList[i]));
                }
            }
            user.setRoles(set);
        }
        System.out.println(user);
        if (!userDao.getById(user.getId()).getPassword().equals(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userDao.update(user);
    }

    @Override
    public User getById(Long id) {
        return userDao.getById(id);
    }

    @Override
    public User getByName(String name) {
        return userDao.getByName(name);
    }

}
