package Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vehicule")
public class Vehicule {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
		private Long id;
		private String marque;
		private String matricule;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getMarque() {
			return marque;
		}
		public void setMarque(String marque) {
			this.marque = marque;
		}
		public String getMatricule() {
			return matricule;
		}
		public void setMatricule(String matricule) {
			this.matricule = matricule;
		}
		@Override
		public String toString() {
			return "Grade [id=" + id + ", marque=" + marque + ", matricule=" + matricule + "]";
		}
		public Vehicule(Long id, String marque, String matricule) {
			super();
			this.id = id;
			this.marque = marque;
			this.matricule = matricule;
		}
		public Vehicule() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		
		
		
		
}

