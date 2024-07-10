package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private long id;
	private String nom ;
	private String adresse;
	private String ville;
	private String codep;
	private String email;
	private String login;
	private String pwd;
	private String role;
	public long getId() {
		return id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getCodep() {
		return codep;
	}
	public void setCodep(String codep) {
		this.codep = codep;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public void setId(long id) {
		this.id = id;
	}
	public User(long id, String nom, String adresse, String ville, String codep, String email, String login, String pwd,
			String role) {
		super();
		this.id = id;
		this.nom = nom;
		this.adresse = adresse;
		this.ville = ville;
		this.codep = codep;
		this.email = email;
		this.login = login;
		this.pwd = pwd;
		this.role = role;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", nom=" + nom + ", adresse=" + adresse + ", ville=" + ville + ", codep=" + codep
				+ ", email=" + email + ", login=" + login + ", pwd=" + pwd + ", role=" + role + "]";
	}
	
}
