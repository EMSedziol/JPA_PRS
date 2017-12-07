package business;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.eclipse.persistence.sessions.serializers.Serializer;

@Entity
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String userName;
	private String passWord;
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	@Column(name="IsReviewer")
	private boolean reviewer;
	@Column(name="IsAdmin")
	private boolean admin;
	@Column(name="IsActive")
	private boolean active;
	private Timestamp dateCreated;
	
	public User() {
		userName = "";
		passWord = "";
	}
	
	public User(String userName, String passWord, String firstName, String lastName,
			String phone, String email, boolean reviewer, boolean admin, boolean active) {
		setId(id);
		setUserName(userName);
		setPassWord(passWord);
		setFirstName(firstName);
		setLastName(lastName);
		setPhone(phone);
		setEmail(email);
		setReviewer(reviewer);
		setAdmin(admin);
		setActive(active);
	}
	
	public Timestamp getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Timestamp date) {
		this.dateCreated = date;
	}

	public int getId() {
		return id;
	}
	public void setId(int rowId) {
		this.id = rowId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public boolean isReviewer() {
		return reviewer;
	}
	public void setReviewer(boolean reviewer) {
		this.reviewer = reviewer;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) { 
		this.active = active;
	}
	
	@Override
	public String toString() {
		return "User [userName=" + userName
				+ ", password=" + passWord
				+ ", firstName=" + firstName
				+ ", lastName=" + lastName
				+ ", phone=" + phone
				+ ", email=" + email
				+ ", reviewer=" + reviewer
				+ ", admin=" + admin
				+ ", active=" + active 
				+ ", dateCreated=" + dateCreated + "]";
	}


}