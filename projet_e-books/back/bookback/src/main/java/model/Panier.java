package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="panier")
public class Panier {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private long id;
private String name;
private float prix ;
public long getId() {
	return id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public float getPrix() {
	return prix;
}
public void setPrix(float prix) {
	this.prix = prix;
}
public void setId(long id) {
	this.id = id;
}
public Panier(long id, String name, float prix) {
	super();
	this.id = id;
	this.name = name;
	this.prix = prix;
}
public Panier() {
	super();
	// TODO Auto-generated constructor stub
}
@Override
public String toString() {
	return "Panier [id=" + id + ", name=" + name + ", prix=" + prix + "]";
}


}
