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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Chat implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idChat;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateChat;
	private String message;
	@ManyToOne
	@JoinColumn(name = "idtravel")
	@JsonIgnore
	private Travel travel;
	@ManyToOne
	@JoinColumn(name = "idemployee")
	@JsonIgnore
	private Employee emlpoyee;

}
