package business;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

public class PurchaseRequest implements Serializable  {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
	@JoinColumn(name="UserId")
	private int userId = 0;
	private String description = "";
	private String justification = "";
	private LocalDate dateNeeded = LocalDate.now();
	private int statusId = 0;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="PurchaseRequestID")
	private List<PurchaseRequestLineItem> lineItems;
	
	public PurchaseRequest() {
		
	}
	
	public PurchaseRequest(int id, int userId, String description, List<PurchaseRequestLineItem> lineItems) {
		super();
		this.id = id;
		this.userId = userId;
		this.description = description;
		this.lineItems = lineItems;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<PurchaseRequestLineItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(List<PurchaseRequestLineItem> lineItems) {
		this.lineItems = lineItems;
	}
	
/*	public static LocalDateTime getDateFrom StringLDT (String date) {
			String dateTime = dateStr + "T00:00:00";
			LocalDateTime dt = LocalDateTime.parse(dataTime);
			return dt;
	}
	
	public Timestamp getDateFromStringTS(String dateStr)
	{
		
	}*/

	@Override
	public String toString() {
		return "[id="+id
				+ ", userId=" + userId
				+ ", description=" + description
				+  "]";
	}

}

