package Model;




import java.util.Date;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name="mission")
public class Mission {

	
	
	@Id
@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Long id;
	private String typem;
	private String iterative;
	private Date datedep;
	private Date dateret;
	private Float kilometrage;
	private Float frais;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTypem() {
		return typem;
	}
	public void setTypem(String typem) {
		this.typem = typem;
	}
	public String getIterative() {
		return iterative;
	}
	public void setIterative(String iterative) {
		this.iterative = iterative;
	}
	public Date getDatedep() {
		return datedep;
	}
	public void setDatedep(Date datedep) {
		this.datedep = datedep;
	}
	public Date getDateret() {
		return dateret;
	}
	public void setDateret(Date dateret) {
		this.dateret = dateret;
	}
	public Float getKilometrage() {
		return kilometrage;
	}
	public void setKilometrage(Float kilometrage) {
		this.kilometrage = kilometrage;
	}
	public Float getFrais() {
		return frais;
	}
	public void setFrais(Float frais) {
		this.frais = frais;
	}
	public Mission(Long id, String typem, String iterative, Date datedep, Date dateret, Float kilometrage,
			Float frais) {
		super();
		this.id = id;
		this.typem = typem;
		this.iterative = iterative;
		this.datedep = datedep;
		this.dateret = dateret;
		this.kilometrage = kilometrage;
		this.frais = frais;
	}
	public Mission() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Mission [id=" + id + ", typem=" + typem + ", iterative=" + iterative + ", datedep=" + datedep
				+ ", dateret=" + dateret + ", kilometrage=" + kilometrage + ", frais=" + frais + "]";
	}

	

	

}
