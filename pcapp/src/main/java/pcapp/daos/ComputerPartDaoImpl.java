package pcapp.daos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pcapp.entities.ComputerPart;

@Repository
public class ComputerPartDaoImpl implements ComputerPartDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Long saveComputerPart(ComputerPart computerPart) {

		return (Long) sessionFactory.getCurrentSession().save(computerPart);
	}

	@Override
	public ComputerPart findByName(String cpName) {
		Session session = sessionFactory.getCurrentSession();
		Query<ComputerPart> query = session.createQuery("FROM ComputerPart WHERE partName=:pName", ComputerPart.class);
		query.setParameter("pName", cpName);
		ComputerPart cp = query.getSingleResult();

		try {
			cp = query.getSingleResult();
		} catch (Exception e) {
			cp = null;
		}
		
		return cp;
	}

	@Override
	public ComputerPart findById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(ComputerPart.class, id);
	}

}
