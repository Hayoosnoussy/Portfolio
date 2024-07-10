package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="produit")
public class Produit {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private long id;
private float prix;
private String nom;
private String langue ;
private String type ;
private String categorie;
private String vendeur;
private String fileName;
private String statut;
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public float getPrix() {
	return prix;
}
public void setPrix(float prix) {
	this.prix = prix;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public String getLangue() {
	return langue;
}
public void setLangue(String langue) {
	this.langue = langue;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getCategorie() {
	return categorie;
}
public void setCategorie(String categorie) {
	this.categorie = categorie;
}
public String getVendeur() {
	return vendeur;
}
public void setVendeur(String vendeur) {
	this.vendeur = vendeur;
}
public String getFileName() {
	return fileName;
}
public void setFileName(String fileName) {
	this.fileName = fileName;
}
public String getStatut() {
	return statut;
}
public void setStatut(String statut) {
	this.statut = statut;
}
public Produit(long id, float prix, String nom, String langue, String type, String categorie, String vendeur,
		String fileName, String statut) {
	super();
	this.id = id;
	this.prix = prix;
	this.nom = nom;
	this.langue = langue;
	this.type = type;
	this.categorie = categorie;
	this.vendeur = vendeur;
	this.fileName = fileName;
	this.statut = statut;
}
public Produit() {
	super();
	// TODO Auto-generated constructor stub
}
@Override
public String toString() {
	return "Produit [id=" + id + ", prix=" + prix + ", nom=" + nom + ", langue=" + langue + ", type=" + type
			+ ", categorie=" + categorie + ", vendeur=" + vendeur + ", fileName=" + fileName + ", statut=" + statut
			+ "]";
}


}
