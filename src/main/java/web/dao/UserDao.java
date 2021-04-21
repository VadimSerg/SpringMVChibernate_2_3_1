package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {


    void saveUser(User user);
    List<User> getUsers();
    User getUserById(Integer id);
    void  update(User user);
    void deleteUserById(Integer id);





}
