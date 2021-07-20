package web.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import web.dao.UserDao;
import web.model.User;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService, UserService {

    private final UserDao userDao;
 //   private final SecurityConfig securityConfig;


    //@Autowired
    public UserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User myUser = userDao.getUserByName(username);

       if (myUser==null) {
           throw new UsernameNotFoundException("User " + username + " not found");
       }

       return  myUser;

    }


    @Override
    public void saveUser(User user) {

//        User userFromDB = userDao.getUserByName(user.getUsername());
//        if (userFromDB!=null) {
//            return;
//        }
//        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
//        user.setPassword(securityConfig.passwordEncoder().encode(user.getPassword()));
        userDao.save(user);
    }


    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }


    @Override
    public User getUserById(long id) {
        return userDao.getOneById(id);
    }



    @Override
    public void update(User user) {
       userDao.update(user);
    }


    @Override
    public void deleteUserById(long id) {
        userDao.deleteById(id);
    }

}
