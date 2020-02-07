package pcapp.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pcapp.entities.Request;

@Repository
public class RequestDaoImpl implements RequestDao {
		
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void createRequest(Request request) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(request);
	}
	
	@Override
	public void updateRequestStatus(Request request) {
		Session session = sessionFactory.getCurrentSession();
		session.update(request);
	}

	@Override
	public Request findRequest(Long id) {
		Session session = sessionFactory.getCurrentSession();
		
		return session.find(Request.class, id);
	}

	@Override
	public List<Request> findRequests() {
		Session session = sessionFactory.getCurrentSession();
	
		return session.createQuery("from Request", Request.class).list();
	}


}
