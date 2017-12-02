package business;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.eclipse.persistence.sessions.Session;
import org.eclipse.persistence.sessions.serializers.Serializer;


@Entity
public class Vendor implements Serializer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String code;
	private String name;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String phone;
	private String email;
	@Column(name="IsPreApproved")
	private boolean isPreApproved;
	@Column(name="IsActive")
	private boolean isActive;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="productId")
	private ArrayList<Product> products;
	
/*	SELECT v.name, v.code, p.partnumber, p.name
	FROM vendor v
	join product p on  v.ID=p.ProductId
	*/
	
	public Vendor() {
		
	}
	
	public Vendor(int id, String code, String name, String address, String city, String state, String zip, String phone,
			String email, boolean isPreApproved, boolean isActive) {
		setId(id);
		setCode(code);
		setName(name);
		setAddress(address);
		setCity(city);
		setState(state);
		setZip(zip);
		setPhone(phone);
		setEmail(email);
		setPreApproved(isPreApproved);
		setActive(isActive);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isPreApproved() {
		return isPreApproved;
	}
	public void setPreApproved(boolean isPreApproved) {
		this.isPreApproved = isPreApproved;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	
	
	@Override
	public String toString() {
		return "Vendor [id=" + id + ", code=" + code + ", name=" + name + ", address=" + address + ", city=" + city
				+ ", state=" + state + ", zip=" + zip + ", phone=" + phone + ", email=" + email + ", isPreApproved="
				+ isPreApproved + ", isActive=" + isActive + ", products=" + products + "]";
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
