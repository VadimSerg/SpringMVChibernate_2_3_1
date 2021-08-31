package web.dao;

import  web.model.User;

import java.util.List;

public interface UserDao {


    void save(User user);
    List<User> getAll();
    User getUserById(long id);
    void  update(User user);
    void deleteById(long id);

    User getUserByName(String name);





}
