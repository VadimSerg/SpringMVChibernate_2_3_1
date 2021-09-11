package web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleDao;
import web.model.Role;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service(value="roleServiceImpl")
@Transactional
public class RoleServiceImpl  implements  RoleService{

    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }


    @Override
    public void saveRole(Role role) {

        roleDao.save(role);

    }

    @Override
    public List<Role> getAllRoles() {

        return roleDao.getAllRoles();
    }

    @Override
    public Role getRoleById(long id) {
        return roleDao.getRoleById(id);
    }

    @Override
    public void update(Role role) {
        roleDao.update(role);
    }

    @Override
    public void deleteRoleById(long id) {
        roleDao.deleteById(id);
    }

    @Override
    public Role getRoleByName(String role) {
        return roleDao.getAuthorityByName(role);
    }


    public Set<Role> getRolesByRoleNames(String[] roleNames) {

            Set<Role> roles = new HashSet<>();
            for (String roleName : roleNames) {
                roles.add((getRoleByName(roleName)));
            }

        return roles;
    }
}