package web.service;

import web.model.User;

import java.util.List;

public interface UserService
//        extends UserDetailsService
                                        {

    void saveUser(User user);

    List<User> getAll();

    User getUserById(long id);

    void  update(User user);

    void deleteUserById(long id);


}