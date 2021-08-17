package web.dao;

import org.springframework.stereotype.Component;
import web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class RoleDaoImpl   {

    @PersistenceContext
    private EntityManager entityManager;


    void save(Role role) {
        entityManager.persist(role);
    }


    List <Role> getAll() {
      return   entityManager.createQuery("select r  from  Role r ",Role.class).getResultList();
    }


    Role getOneById(long id) {
        return  entityManager.find(Role.class, id);
    }

    void  update(Role role) {
        entityManager.merge(role);
    }


    void deleteById(long id) {
         entityManager.remove(getOneById(id));
    }

    Role getAuthorityByName(String name) {
        return  entityManager.
                createQuery("select r from Role r where r.role =:role" ,Role.class).
                setParameter("role",name).getSingleResult();
    }






}
