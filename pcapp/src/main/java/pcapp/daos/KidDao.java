package pcapp.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pcapp.entities.Kid;

@Repository
public class KidDao {
	
	@Autowired
	private SessionFactory sessionFactory;

// creating
	public void saveThisKid(Kid kid) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(kid);
	}
	
// reading

	//all
	public List<Kid> getAllKids(){
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Kid> theQuery = currentSession.createQuery("from Kid order by firstName", Kid.class);
		List<Kid> kids = theQuery.getResultList();
		
		return kids;
	}
	
	// one
	public Kid getKid(int theId) {
		
		Session currentSession = sessionFactory.getCurrentSession();

		Kid kid = currentSession.get(Kid.class, theId);
		
		return kid;
	}
	
	
// updating == saving method()

	
// deleting
	public void deleteKid(int theId) {

		Session currentSession = sessionFactory.getCurrentSession();

		Query theQuery = currentSession.createQuery("delete from Kid where id=:kidId");
		theQuery.setParameter("kidId", theId);
		
		theQuery.executeUpdate();		
	}
	
}
