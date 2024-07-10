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
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Post implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idPost;
	private String descritpion;
	private String Image;
	@Temporal(TemporalType.DATE)
	private Date datePost;
	@JsonIgnore
	@ManyToOne
	private Employee employee;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="post")
	private List<Likee> likes;
	@OneToMany(cascade=CascadeType.ALL,mappedBy="post")
	private List<Comment> comments;
	
}