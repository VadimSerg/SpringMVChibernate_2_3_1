package web.service;

import web.model.Role;

import java.util.List;

public interface RoleService {
    void saveRole(Role role);

    List<Role> getAllRoles();

    Role getRoleById(long id);

    void  update(Role role);

    void deleteRoleById(long id);

    Role getRoleByName(String role);


}
