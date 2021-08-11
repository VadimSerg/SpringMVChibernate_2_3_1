package web.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

import java.util.List;

@Service(value="userServiceImpl")
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao userDao;


    public UserServiceImpl(UserDao userDao) {
       this.userDao = userDao;
    }


    @Override
    public void saveUser(User user) {
        userDao.save(user);
    }


    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }


    @Override
    public User getUserById(long id) {
        return  userDao.getOneById(id);
    }


    @Override
    public void update(User user) {
        userDao.update(user);
    }


    @Override
    public void deleteUserById(long id) {
        userDao.deleteById(id);
    }

//    @Override
//    public UserDetails getUserByUsername(String username) {
//        return userDao.getUserByName(username);
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User myUser = userDao.getUserByName(username);

        if (myUser==null) {
           throw new UsernameNotFoundException("User " + username + " not found");
        }

        return  myUser;
    }

}