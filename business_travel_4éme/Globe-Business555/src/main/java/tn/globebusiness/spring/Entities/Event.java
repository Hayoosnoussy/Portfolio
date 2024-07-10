package tn.globebusiness.spring.Entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Event implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer eventId;
	@Temporal(TemporalType.DATE)
	Date startDate;
	String duration;
	String description;
	String title;
	String location;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "category_id")
	Category category;

	
	  @JsonBackReference(value = "event_company")
	  @ManyToOne
	  @JoinColumn(name = "company_id") Company company;
	

	@OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
	List<Rating> ratings;

	/*
	 * @OneToMany(mappedBy = "event", cascade = CascadeType.ALL) private
	 * List<EventInvitation> eventInvitaions = new ArrayList<>();
	 */

}
