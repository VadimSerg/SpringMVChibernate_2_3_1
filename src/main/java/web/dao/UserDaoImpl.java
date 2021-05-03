package web.dao;

import org.springframework.stereotype.Component;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Component
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    public List<User> getUsers() {
        return  entityManager.createQuery("select u from User u", User.class).
                getResultList();
    }


    public User getUserById(Integer id) {
        return entityManager.find(User.class, id);
    }


    public void update(User user) {
        entityManager.merge(user);
    }


    public void deleteUserById(Integer id) {
        entityManager.remove(getUserById(id));
    }
}




