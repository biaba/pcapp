package pcapp.daos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pcapp.entities.Role;

@Repository
public class RoleDaoImpl implements RoleDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Role findRoleByName(String roleName) {
		// getting current session
		Session session = sessionFactory.getCurrentSession();
		
		// checking if role exists
		Query<Role> query = session.createQuery("FROM Role WHERE name=:rName", Role.class );
		query.setParameter("rName", roleName);
		Role checkedRole = null;
		try {
			checkedRole = query.getSingleResult();
		}
		catch(Exception e) {
			checkedRole = null;
		}
		return checkedRole;
	}

}
