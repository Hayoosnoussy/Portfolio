package tn.globebusiness.spring.Entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Travel implements Serializable {
	@Id 
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id ;
	@Temporal(TemporalType.DATE)
	private Date date_begin ;
	private String destination ;
	private String state;
	private String city;
	private Date date_end ;
	private String objective ;
	@ManyToOne
	private Company company;
	@OneToOne
	private Employee employee ;
	@OneToMany(cascade=CascadeType.ALL,mappedBy="travel")
	private List<FeedBack> feedBacks;
	@OneToMany(cascade=CascadeType.ALL,mappedBy="travel")
	private List<Complaint> complaints;
}
