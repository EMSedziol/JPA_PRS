package business;

import java.io.Serializable;
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

	@Override
	public String toString() {
		return "[id="+id
				+ ", userId=" + userId
				+ ", description=" + description
				+  "]";
	}
	/*public String getJustification() {
		return justification;
	}
	public void setJustification(String justification) {
		this.justification = justification;
	}
	public String getDateNeeded() {
		return dateNeeded;
	}
	public void setDateNeeded(String dateNeeded) {
		this.dateNeeded = dateNeeded;
	}
	public String getDeliveryMode() {
		return deliveryMode;
	}
	public void setDeliveryMode(String deliveryMode) {
		this.deliveryMode = deliveryMode;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getSubmittedDate() {
		return submittedDate;
	}
	public void setSubmittedDate(String submittedDate) {
		this.submittedDate = submittedDate;
	}
	public String getReasonForRejection() {
		return reasonForRejection;
	}
	public void setReasonForRejection(String reasonForRejection) {
		this.reasonForRejection = reasonForRejection;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}*/
		
	/*@Override
	public String toString() {
		return "[id="+id
				+ ", userId=" + userId
				+ ", description=" + description
				+ ", justification=" + justification
				+ ", dateNeeded=" + dateNeeded
				+ ", deliveryMode=" + deliveryMode
				+ ", statusId=" + statusId
				+ ", total=" + total
				+ ", submittedDate=" + submittedDate
				+ ", reasonForRejection=" + reasonForRejection
				+ ", active=" + active + "]";
	}*/

}

