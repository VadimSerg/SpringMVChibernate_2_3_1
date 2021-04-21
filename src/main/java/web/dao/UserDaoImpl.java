package web.dao;

import web.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;



    @Override
    public void saveUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUsers() {

        TypedQuery<User> typedQuery =sessionFactory.getCurrentSession().createQuery("from User");
        return typedQuery.getResultList();

    }

    @Override
    @SuppressWarnings("unchecked")
    public User getUserById(Integer id) {
        return sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Override
    public void update(User user) {
        User userUpdated =getUserById(user.getId());
        userUpdated.setFirstName(user.getFirstName());
        userUpdated.setSurname(user.getSurname());
        userUpdated.setAge(user.getAge());
        userUpdated.setCity(user.getCity());
        sessionFactory.getCurrentSession().update(userUpdated);
    }

    @Override
    public void deleteUserById(Integer id) {
       sessionFactory.getCurrentSession().delete(getUserById(id));
    }
}
