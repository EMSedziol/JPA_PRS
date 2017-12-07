package business;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.eclipse.persistence.sessions.Session;
import org.eclipse.persistence.sessions.serializers.Serializer;

 @Entity
 public class Status implements Serializer {
	@Id
	private int id;
	private String description;
	
	public Status() {

	}
	public Status(String description) {
		setDescription(description);
	}
	public Status(int id, String description) {
		setId(id);
		setDescription(description);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Status [id=" + id + ", description=" + description + "]";
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
