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
public class Comment implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idComment;
	private String comm;
	@Temporal(TemporalType.DATE)
	private Date datePost;
	@ManyToOne
	@JoinColumn(name="idpost")
	@JsonIgnore
	private Post post;
	@ManyToOne
	@JoinColumn(name="idemployee")
	@JsonIgnore
	private Employee employee;
	
}
