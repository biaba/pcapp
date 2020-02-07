package pcapp.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pcapp.entities.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Long saveUser(User user) {
		// getting hibernate session
		Session session = sessionFactory.getCurrentSession();
		// saving user/ returning Id
		Long userId =  (Long) session.save(user);
		return userId;
	}

	@Override
	public User findByUserName(String userName) {
		// getting hibernate session
		Session session = sessionFactory.getCurrentSession();

		// check if user exists by userName
		Query<User> query = session.createQuery("FROM User WHERE username=:uName", User.class);
		query.setParameter("uName", userName);
		User checkedUser = null;
		
		try {
			checkedUser = query.getSingleResult();
		}
		catch(Exception e) {
			checkedUser = null;
		}
		
		return checkedUser;
	}

	@Override
	public User findById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		
		return session.find(User.class, id);
	}

	@Override
	public void deleteUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(user);
	}

	@Override
	public List<User> loadUsers() {
		
		Session session = sessionFactory.getCurrentSession();
		Query<User> query = session.createQuery("from User order by username", User.class);
		return query.getResultList();
	}
	

}
