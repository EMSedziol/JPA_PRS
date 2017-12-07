package business;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import db.DBUtil;

public class StatusDB {
	
	public static Status getUserByInt(int getId) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try {
			Status status = em.find(Status.class, getId);
			return status;
		}
		finally {
			em.close();
		}
	}

	public static ArrayList<Status> getAllStatus() {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            TypedQuery<Status> query = em.createQuery("SELECT s FROM Status s", Status.class);
            ArrayList<Status> allStatus = new ArrayList<>(query.getResultList());
            return allStatus;
        }
        finally {
            em.close();
        }
    }
	
}
