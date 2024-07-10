package tn.globebusiness.spring.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Post implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_post")
	private Long idPost;
	private String description;
	private String Image;
	@Temporal(TemporalType.DATE)
	private Date datePost;
	
	@ManyToOne
	@JsonIgnore
	private Employee employee;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="post")
	private List<Likee> likes;
	@OneToMany(cascade=CascadeType.ALL,mappedBy="post")
	private List<Comment> comments;

}