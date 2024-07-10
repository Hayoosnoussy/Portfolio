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

@Entity
@Data
public class Share implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idShare;
	@Temporal(TemporalType.DATE)
	private Date dateShare;
	private String message;
	@ManyToOne
	@JoinColumn(name="idEmployee1")
	private Employee employee1;
	@ManyToOne
	@JoinColumn(name="idEmployee2")
	private Employee employee2;
	@ManyToOne
	@JoinColumn(name="idpost")
	private Post post;
}
