package db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import business.User;

public class DBUtil {
	private static final EntityManagerFactory emf =
			Persistence.createEntityManagerFactory("JPA_PRS");
	public static EntityManagerFactory getEmFactory() {
		return emf;
	}

	public static boolean addUser(User u) {
		boolean success = false;
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		try {
			et.begin();
			em.persist(u);
			et.commit();
		}
		catch (Exception e) {
			et.getRollbackOnly();
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		return success;
	}
}
