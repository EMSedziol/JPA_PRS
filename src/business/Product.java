package business;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.eclipse.persistence.sessions.Session;
import org.eclipse.persistence.sessions.serializers.Serializer;

@Entity
public class Product implements Serializer {
	private static final Product product = null;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int vendorId;
	private int productId;
	private String partNumber;
	private String name;
	private double price;
	private String unit;
	private String photopath;
	private boolean isActive;
	
	public Product() {
/*		id = 0; 
		code = "";
		description = "";
		price = 0.0;*/
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVendorId() {
		return vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

	public int getProductId() {
		return productId;
	}



	public void setProductId(int productId) {
		this.productId = productId;
	}



	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getPhotopath() {
		return photopath;
	}

	public void setPhotopath(String photopath) {
		this.photopath = photopath;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public static Product getProduct() {
		return product;
	}
	
	@Override
	public String toString() {
		return "\nProduct [id=" + id + ", vendorId=" + vendorId + ", productId=" + productId + ", partNumber="
				+ partNumber + ", name=" + name + ", price=" + price + ", unit=" + unit + ", photopath=" + photopath
				+ ", isActive=" + isActive + "]";
	}

	@Override
	public Object deserialize(Object arg0, Session arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initialize(Class arg0, String arg1, Session arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object serialize(Object arg0, Session arg1) {
		// TODO Auto-generated method stub
		return null;
	}


	
}
