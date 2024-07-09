package Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="service")
public class Service {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY) 
	
	private Long id;
private String servUser;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getServUser() {
	return servUser;
}
public void setServUser(String servUser) {
	this.servUser = servUser;
}
@Override
public String toString() {
	return "Service [id=" + id + ", servUser=" + servUser + "]";
}
public Service(Long id, String servUser) {
	super();
	this.id = id;
	this.servUser = servUser;
}
public Service() {
	super();
	// TODO Auto-generated constructor stub
}


}
