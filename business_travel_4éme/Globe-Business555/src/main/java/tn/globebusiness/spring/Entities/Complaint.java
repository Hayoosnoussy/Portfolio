package tn.globebusiness.spring.Entities;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
@Entity
@Data
@Table(name="complaint")
public class Complaint implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idC",nullable = false)
	private Long idC;
	@Column(name ="title")
	private String title;
	@Column(name ="description")
	private String description;
	private int etat;
	@Temporal(TemporalType.TIMESTAMP)
	private Date feedbackDate;
	@ManyToOne
	@JsonIgnore
	private Travel travel;
	@ManyToOne
	@JsonIgnore
	private Employee employee;

}