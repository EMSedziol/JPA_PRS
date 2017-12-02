package business;

import javax.persistence.EntityManager;

import db.DBUtil;

public class ProductDB {
	
	public static Product getProductByInt(int productId) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		Product prd = null;
		try {
			prd = em.find(Product.class, productId);
			// return product;
		}
		finally {
			em.close();
		}
		return prd;
	}
}
