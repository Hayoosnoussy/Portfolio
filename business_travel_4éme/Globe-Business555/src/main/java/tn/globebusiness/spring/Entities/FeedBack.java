package tn.globebusiness.spring.Entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class FeedBack implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idF")
	private Long idF;
	private String content;
	private int rating;
	@Temporal(TemporalType.TIMESTAMP)
	Date feedbackDateAndTime;
	@ManyToOne
	@JsonIgnore
	private Travel travel;
	@ManyToOne
	@JsonIgnore
	private Employee employee;

	
	
	
}