package Model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;





@Entity
@Table(name="user")
public class User {

	
	
	@Id
      @GeneratedValue(strategy=GenerationType.IDENTITY) 
private Long id;
private String nom;
private String prenom;


private String login;
private String pwd;
private String role;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public String getPrenom() {
	return prenom;
}
public void setPrenom(String prenom) {
	this.prenom = prenom;
}
public String getLogin() {
	return login;
}
public void setLogin(String login) {
	this.login = login;
}
public String getPwd() {
	return pwd;
}
public void setPwd(String pwd) {
	this.pwd = pwd;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
public User(Long id, String nom, String prenom, String login, String pwd, String role) {
	super();
	this.id = id;
	this.nom = nom;
	this.prenom = prenom;
	this.login = login;
	this.pwd = pwd;
	this.role = role;
}
public User() {
	super();
	// TODO Auto-generated constructor stub
}

}
