package web.dao;

//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import  javax.persistence.TypedQuery;
//import web.model.User;

//import java.util.List;


import org.springframework.stereotype.Component;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


//@Repository
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
 //       User userUpdated =getUserById(user.getId());
//      userUpdated.setFirstName(user.getFirstName());
//      userUpdated.setSurname(user.getSurname());
//      userUpdated.setAge(user.getAge());
//      userUpdated.setCity(user.getCity());
        entityManager.merge(user);

    }

    public void deleteUserById(Integer id) {
        entityManager.remove(getUserById(id));

    }
}













//    @Autowired
//    private SessionFactory sessionFactory;
//
//
//
//    @Override
//    public void saveUser(User user) {
//        sessionFactory.getCurrentSession().save(user);
//    }
//
//    @Override
//    @SuppressWarnings("unchecked")
//    public List<User> getUsers() {
//
//        TypedQuery<User> typedQuery =sessionFactory.getCurrentSession().createQuery("from User");
//        return typedQuery.getResultList();
//
//    }
//
//    @Override
//    @SuppressWarnings("unchecked")
//    public User getUserById(Integer id) {
//        return sessionFactory.getCurrentSession().get(User.class, id);
//    }
//
//    @Override
//    public void update(User user) {
//        User userUpdated =getUserById(user.getId());
//        userUpdated.setFirstName(user.getFirstName());
//        userUpdated.setSurname(user.getSurname());
//        userUpdated.setAge(user.getAge());
//        userUpdated.setCity(user.getCity());
//        sessionFactory.getCurrentSession().update(userUpdated);
//    }
//
//    @Override
//    public void deleteUserById(Integer id) {
//       sessionFactory.getCurrentSession().delete(getUserById(id));
//    }
//}




