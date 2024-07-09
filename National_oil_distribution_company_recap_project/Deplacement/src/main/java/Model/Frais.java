package Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="frais")
public class Frais {
	
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    private double totPetitDejouner;
	    private double totDinner;
	    private double totPrerep;
	    private double totHebergement;
	    private double tauxKilometrique;
	    private double divers;
	   private double total;
	    
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public double getTotPetitDejouner() {
			return totPetitDejouner;
		}
		public void setTotPetitDejouner(double totPetitDejouner) {
			this.totPetitDejouner = totPetitDejouner;
		}
		public double getTotDinner() {
			return totDinner;
		}
		public void setTotDinner(double totDinner) {
			this.totDinner = totDinner;
		}
		public double getTotPrerep() {
			return totPrerep;
		}
		public void setTotPrerep(double totPrerep) {
			this.totPrerep = totPrerep;
		}
		public double getTotHebergement() {
			return totHebergement;
		}
		public void setTotHebergement(double totHebergement) {
			this.totHebergement = totHebergement;
		}
		public double getTauxKilometrique() {
			return tauxKilometrique;
		}
		public void setTauxKilometrique(double tauxKilometrique) {
			this.tauxKilometrique = tauxKilometrique;
		}
		public double getDivers() {
			return divers;
		}
		public void setDivers(double divers) {
			this.divers = divers;
		}
		public double getTotal() {
			return total;
		}
		public void setTotal(double total) {
			this.total = total;
		}

		public Frais(Long id, double totPetitDejouner, double totDinner, double totPrerep, double totHebergement,
				double tauxKilometrique, double divers, double total, Mission mission) {
			super();
			this.id = id;
			this.totPetitDejouner = totPetitDejouner;
			this.totDinner = totDinner;
			this.totPrerep = totPrerep;
			this.totHebergement = totHebergement;
			this.tauxKilometrique = tauxKilometrique;
			this.divers = divers;
			this.total = total;
		
		}
		public Frais() {
			super();
			// TODO Auto-generated constructor stub
		}
		@Override
		public String toString() {
			return "Frais [id=" + id + ", totPetitDejouner=" + totPetitDejouner + ", totDinner=" + totDinner
					+ ", totPrerep=" + totPrerep + ", totHebergement=" + totHebergement + ", tauxKilometrique="
					+ tauxKilometrique + ", divers=" + divers + ", total=" + total + "]";
		}
	
	    
}
