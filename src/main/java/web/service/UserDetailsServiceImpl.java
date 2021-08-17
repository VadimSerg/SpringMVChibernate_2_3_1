package web.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import web.dao.UserDao;
import web.model.User;

@Service(value="userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDao userDao;
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
}
