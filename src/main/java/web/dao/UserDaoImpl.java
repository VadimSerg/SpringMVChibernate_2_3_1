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
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> getAll() {
        return  entityManager.createQuery("select u from User u", User.class).
                getResultList();
    }

    @Override
    public User getOneById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteById(long id) {
        entityManager.remove(getOneById(id));
    }

    @Override
    public User getUserByName(String name) {
        return entityManager.createQuery("select  u from User u where u.firstName =:name",User.class).
                setParameter("name",name).getSingleResult();
    }
}




