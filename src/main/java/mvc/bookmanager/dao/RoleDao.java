package mvc.bookmanager.dao;

import mvc.bookmanager.model.Role;
import mvc.bookmanager.model.User;

import java.util.List;


public interface RoleDao {
    public void addUser(Role role);

    public void removeRole(int id);

    public Role getRoleById(long id);

    public List<Role> listRoles();
}
