package pcapp.daos;

import pcapp.entities.Role;

public interface RoleDao {
	
	public Role findRoleByName(String roleName);

}
