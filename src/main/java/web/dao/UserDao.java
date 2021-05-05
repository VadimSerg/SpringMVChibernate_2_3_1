package web.dao;

import  web.model.User;

import java.util.List;

public interface UserDao {


    void saveUser(User user);
    List<User> getUsers();
    User getUserById(long id);
    void  update(User user);
    void deleteUserById(long id);





}
