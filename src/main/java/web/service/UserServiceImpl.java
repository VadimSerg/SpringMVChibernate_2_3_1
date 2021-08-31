package web.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleDao;
import web.dao.UserDao;
import web.model.User;

import java.util.List;

@Service(value="userServiceImpl")
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private  final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;

   public UserServiceImpl(UserDao userDao, RoleDao roleDao, PasswordEncoder passwordEncoder) {
       this.userDao = userDao;
       this.roleDao = roleDao;
       this.passwordEncoder = passwordEncoder;
   }



    @Override
    public void saveUser(User user) {



      //  Set<Role> updatedRoles = new HashSet<>((Collection<? extends Role>) user.getAuthorities());
     // user.setRoles(user.getRoles());

        System.out.println("************SAVING PROCESS********************************");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);

        System.out.println("UserID:" + user.getId() +
                "with rolename " +
                //user.getRoles().toArray().toString()+
                " was saved");///ВЫВОД ---id user присваивается в дб после
        // сохранения , но никак не до сохранения

    }


    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }


    @Override
    public User getUserById(long id) {
        return  userDao.getUserById(id);
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