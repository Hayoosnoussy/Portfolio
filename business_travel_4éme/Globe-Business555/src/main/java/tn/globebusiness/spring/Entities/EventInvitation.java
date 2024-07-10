package tn.globebusiness.spring.Entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class EventInvitation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer invitationId;
	@Temporal(TemporalType.DATE)
	Date date;
	@Enumerated(EnumType.STRING)
	State state;

	@JsonBackReference(value = "eventInvitation_employee")
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "employee_id")
	Employee employee;
	
	@JsonBackReference(value = "eventInvitation_company")
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "company_id")
	Company company;

	@JsonBackReference(value = "event_invitation")
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "event_id")
	Event event;

}
