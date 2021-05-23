package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import web.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao{

    private UserRepository userRepository;

    private final RoleDao roleDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public UserDaoImpl(RoleDao roleDao, UserRepository userRepository) {
        this.roleDao = roleDao;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> allUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public void add(User user) {
        entityManager.merge(user);
    }

    @Override
    public void delete(User user) {

//        entityManager.remove(user);
        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(Long id) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u where u.id=:id",User.class);
        query.setParameter("id", id);
        return query.getResultList().stream().findAny().orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public User getByName(String name) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u where u.name=:name" ,User.class);
        query.setParameter("name", name);
        return query.getResultList().stream().findAny().orElse(null);
    }
}
