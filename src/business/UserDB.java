package business;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import db.DBUtil;

public class UserDB {
	public static User getUserByInt(int getId) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try {
			User user = em.find(User.class, getId);
			return user;
		}
		finally {
			em.close();
		}
	}
	
	public static User getUserById(int getId) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try {
			User user = em.find(User.class, getId);
			return user;
		}
		finally {
			em.close();
		}
	}
	
	public static boolean deleteUser(User u) {
		boolean success = false;
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		try {
			et.begin();
			em.remove(em.merge(u));
			et.commit();
			success = true;
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
	
	public static boolean addUser(User u) {
		boolean success = false;
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		try {
			et.begin();
			em.persist(u);
	//		em.flush();  ///????
			et.commit();
			success = true;
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
	
	public static boolean updateUser(User u) {
		boolean success = false;
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		try {
			et.begin();
			em.merge(u);
			et.commit();
			success = true;
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
	
	public static ArrayList<User> getAllUsers() {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
            ArrayList<User> allUsers = new ArrayList<>(query.getResultList());
            return allUsers;
        }
        finally {
            em.close();
        }
    }
	
	public static User validateUser(String userName, String password) {
		User usr = null;
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String jpsql = "select u from User u where u.userName = :uname and u.passWord = :pwd";
		TypedQuery<User> q = em.createQuery(jpsql, User.class);
		q.setParameter("uname", userName);
		q.setParameter("pwd", password);
		try {
			usr = q.getSingleResult();
		} catch (NoResultException nex) {
			System.out.println("User "+ userName+ " not found");
			
		}
		return usr;
	}

}
