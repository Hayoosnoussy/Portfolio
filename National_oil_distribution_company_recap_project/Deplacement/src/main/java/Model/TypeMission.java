package Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="typemission")
public class TypeMission { 
	@Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Long id;
	private String tyoem;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTyoem() {
		return tyoem;
	}
	public void setTyoem(String tyoem) {
		this.tyoem = tyoem;
	}
	@Override
	public String toString() {
		return "TypeMission [id=" + id + ", tyoem=" + tyoem + "]";
	}
	public TypeMission(Long id, String tyoem) {
		super();
		this.id = id;
		this.tyoem = tyoem;
	}
	public TypeMission() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
