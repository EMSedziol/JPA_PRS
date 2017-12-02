package business;

import javax.persistence.EntityManager;

import db.DBUtil;

public class VendorDB {
	public static Vendor getVendorByInt(int getId) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try {
			Vendor vendor = em.find(Vendor.class, getId);
			return vendor;
		}
		finally {
			em.close();
		}
	}
}