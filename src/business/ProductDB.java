package business;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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
	
	public static ArrayList<Product> getAllProducts() {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            TypedQuery<Product> query = em.createQuery("SELECT p FROM User p", Product.class);
            ArrayList<Product> allProducts = new ArrayList<>(query.getResultList());
            return allProducts;
        }
        finally {
            em.close();
        }
    }
}
