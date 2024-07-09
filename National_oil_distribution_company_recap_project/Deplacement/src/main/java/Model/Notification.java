package Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="notification")
public class Notification {
  @Id
 @GeneratedValue(strategy=GenerationType.IDENTITY) 
  private Long id;
  private String notification;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getNotification() {
	return notification;
}
public void setNotification(String notification) {
	this.notification = notification;
}
@Override
public String toString() {
	return "Notification [id=" + id + ", notification=" + notification + "]";
}
public Notification(Long id, String notification) {
	super();
	this.id = id;
	this.notification = notification;
}
public Notification() {
	super();
	// TODO Auto-generated constructor stub
}
  
   
	
}
