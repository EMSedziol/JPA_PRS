package business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.Path;

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
	
	public static boolean addUser(User u2) {
		boolean success = false;
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		try {
			et.begin();
			em.persist(u2);
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
	
	public static User validateUserName(String userName) {
		User usr = null;
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String jpsql = "select u from User u where u.userName = :uname";
		TypedQuery<User> q = em.createQuery(jpsql, User.class);
		q.setParameter("uname", userName);
		try {
			usr = q.getSingleResult();
			System.out.println("in validateUserName ");
			System.out.println(usr.getUserName() + " found");
		} catch (NoResultException nex) {
			System.out.println("User "+ userName+ " could not be validate");
			
		}
		return usr;
	}

//	public static void addUsersFromTabfile() throws FileNotFoundException {
	public static void addUsersFromTabfile() {
		String FilePathName = "C:\\bootcampJavaRepo\\JPA_PRS\\src\\business\\JavaUsers.txt";
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(FilePathName)));
		} catch (FileNotFoundException e1) {
			System.out.println("Could not retreive file");
			e1.printStackTrace();
		}

		String line = null;

		try {
			while ((line = reader.readLine()) != null) {
				String[] fields = line.split("\\t");
				String userName = fields[0];
				System.out.println("userName " + userName);
				User u = UserDB.validateUserName(userName);
				if (u != null) {
					System.out.println("user '" + u.getUserName() + "' already in database");
				} else {
					String passWord = fields[1];
					String firstName = fields[2];
					String lastName = fields[3];
					String phone = fields[4];
					String email = fields[5];
					Boolean reviewer = Boolean.valueOf(fields[6]);
					Boolean admin = Boolean.valueOf(fields[7]);
					Boolean active = true;

					User u2 = new User(userName, passWord, firstName, lastName, phone, email, reviewer, admin, active);
					u2.setDateCreated(new Timestamp(System.currentTimeMillis()));

					if (UserDB.addUser(u2)) {
						System.out.println("User " + userName + " added");
					} else
						System.out.println("User " + userName + " was not added");
				}

			}
		} catch (IOException e) {
			System.out.println(" Could not read file");
			e.printStackTrace();
		}
	}

	// public static void addUsersFromFlatfile() throws FileNotFoundException {
	public static void addUsersFromFlatfile() {
		String FilePathName = "C:\\bootcampJavaRepo\\JPA_PRS\\src\\business\\UsersFlatFile.txt";
		System.out.println("Paht/File" + FilePathName);
		BufferedReader reader = null;
		try {
			System.out.println("in first try");
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(FilePathName)));
		} catch (FileNotFoundException e1) {
			System.out.println("Could not read file");
			e1.printStackTrace();
		}

			String line = null;

			try {
				while ((line = reader.readLine()) != null) {
					// String[] fields = line.split("\\t");
					String userName = line.substring(0, 19);
					User u = UserDB.validateUserName(userName);
					if (u != null) {
						System.out.println("user '" + u.getUserName() + "' already in database");
					} else {

						String passWord = line.substring(20, 29).trim();
						String firstName = line.substring(30, 49).trim();
						String lastName = line.substring(50, 69).trim();
						String phone = line.substring(70, 79).trim();
						String email = "";
						Boolean reviewer = false;
						Boolean admin = false;
						Boolean active = true;

						User u2 = new User(userName, passWord, firstName, lastName, phone, email, reviewer, admin,
								active);
						u2.setDateCreated(new Timestamp(System.currentTimeMillis()));

						if (UserDB.addUser(u2)) {
							System.out.println("User " + userName + " added");
						} else
							System.out.println("User " + userName + " was not added");
					}

				}
			} catch (IOException e) {
				System.out.println(" Could not read file");
				e.printStackTrace();
			}
		
	}

}
