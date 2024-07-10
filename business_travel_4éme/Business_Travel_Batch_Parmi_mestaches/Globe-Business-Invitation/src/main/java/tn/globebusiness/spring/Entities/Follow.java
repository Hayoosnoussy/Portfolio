package tn.globebusiness.spring.Entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
@Data
@Entity
public class Follow implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idFollow;
	@Temporal(TemporalType.DATE)
	private Date dateFollow;
	private int etat;
	@ManyToOne
	@JoinColumn(name="idEmployee1")
	private Employee employee1;
	@ManyToOne
	@JoinColumn(name="idEmployee2")
	private Employee employee2;
}
