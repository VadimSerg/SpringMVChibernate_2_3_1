package web.dao;

import web.model.Role;

import java.util.List;

public interface RoleDao  {

    void save(Role role);
    List<Role> getAllRoles();
    Role getRoleById(long id);
    void  update(Role role);
    void deleteById(long id);

    Role getAuthorityByName(String name);


}
