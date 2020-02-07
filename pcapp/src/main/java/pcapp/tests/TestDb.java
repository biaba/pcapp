package pcapp.tests;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import pcapp.daos.ComputerPartDao;
import pcapp.daos.ComputerPartDaoImpl;
import pcapp.daos.UserDaoImpl;
import pcapp.entities.ComputerPart;
import pcapp.entities.Request;
import pcapp.entities.RequestStatus;
import pcapp.entities.Role;
import pcapp.entities.User;
import pcapp.models.Kid;

public class TestDb {

	public static void main(String[] args) throws ParseException {

		// CRUD for request

		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.addAnnotatedClass(Role.class).addAnnotatedClass(Request.class).addAnnotatedClass(ComputerPart.class).addAnnotatedClass(Kid.class)
				.buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();

		ComputerPartDaoImpl computerPartDao = new ComputerPartDaoImpl();
		UserDaoImpl userDao = new UserDaoImpl();

		try {

			// Creating request

			/*
			 * session.beginTransaction();
			 * 
			 * Request rq1 = new Request(RequestStatus.SUBMITTED, "12/07/2019",
			 * "just needs update"); rq1.setUserId(1l);
			 * 
			 * User u1 = session.get(User.class, 1l); u1.addRequest(rq1);
			 * session.saveOrUpdate(u1);
			 * 
			 * session.getTransaction().commit();
			 * 
			 */
			// Reading request

			/*
			 * session.beginTransaction();
			 * 
			 * User u2 = session.get(User.class, 3l);
			 * 
			 * System.out.println(u2);
			 * 
			 * session.getTransaction().commit();
			 */
			// updating request status
			/*
			 * session.beginTransaction();
			 * 
			 * Request r = session.get(Request.class, 32l);
			 * r.setRequestStatus(RequestStatus.COMPLETED); session.save(r);
			 * session.getTransaction().commit();
			 * 
			 * 
			 */
			// updating request
			/*
			 * session.beginTransaction();
			 * 
			 * Request rq3 = session.get(Request.class, 2l); ComputerPart p1 =
			 * session.get(ComputerPart.class, 1l); ComputerPart p2 =
			 * session.get(ComputerPart.class, 2l); ComputerPart p3 =
			 * session.get(ComputerPart.class, 3l); Set<ComputerPart> cpSet = new
			 * HashSet<>(); cpSet.add(p1); cpSet.add(p2); cpSet.add(p3);
			 * rq3.setComputerParts(cpSet);
			 * 
			 * rq3.setRequestStatus(RequestStatus.NOT_SUBMITTED); session.saveOrUpdate(rq3);
			 * 
			 * session.getTransaction().commit();
			 */
			// deleting request
			/*
			 * session.beginTransaction();
			 * 
			 * Request rq4 = session.get(Request.class, 1l);
			 * 
			 * 
			 * 
			 * session.delete(rq4);
			 * 
			 * session.getTransaction().commit();
			 */

			/*
			 * session.beginTransaction();
			 * 
			 * User u5= new User("uno", "test123", "uno@inbox.lv"); session.save(u5);
			 * session.getTransaction().commit();
			 */

			// reading computer part
			/*
			session.beginTransaction();
			String cpName = "SSD";
			Query<ComputerPart> query = session.createQuery("FROM ComputerPart WHERE partName=:pName",
					ComputerPart.class);
			query.setParameter("pName", cpName);

			ComputerPart cp = query.getSingleResult();
			System.out.println(cp);

			session.getTransaction().commit();
			
			*/
			
			// getting User list
			/*
			session.beginTransaction();
			Query<User> query = session.createQuery("from User order by username", User.class);
			System.out.println(query.getResultList());
			
			session.getTransaction().commit();
			*/
			
			// deleting user with not allowed roleName
			/*session.beginTransaction();
			
			User u3 = session.get(User.class, 4l);
			
			try {
				
				// checking user roles
				for(Role r: u3.getRoles()) {
					if(r.getRoleName().equals("ROLE_APPLICANT")) {
						throw new Exception ("can not delete");
					}
				}
				userDao.deleteUser(u3);
			}
			catch(Exception e) {
				System.out.println("User can not be deleted");
			}
			
			session.getTransaction().commit();
			*/
			session.beginTransaction();
			
			Kid kid = new Kid();
			//kid.setId((long)0);
			kid.setFirstName("erna");
			System.out.println(kid.getFirstName()+" "+ kid.getId());
			 session.saveOrUpdate(kid);
			 
			 session.getTransaction().commit();

		} finally {

			session.close();
		}

	}

}
