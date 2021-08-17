package web.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

import java.util.List;

@Service(value="userServiceImpl")
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

   public UserServiceImpl(UserDao userDao,PasswordEncoder passwordEncoder) {
       this.userDao = userDao;
       this.passwordEncoder = passwordEncoder;
   }



    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(user.getRoles());
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

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.update(user);

    }


    @Override
    public void deleteUserById(long id) {
        userDao.deleteById(id);
    }



}