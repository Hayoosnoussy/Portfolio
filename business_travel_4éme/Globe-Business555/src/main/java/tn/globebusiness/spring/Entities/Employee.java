package tn.globebusiness.spring.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String lastName;

	@Temporal(TemporalType.DATE)
	private Date birthday;

	private String email;

	private Long phone;

	@Column(name = "login", unique = true)
	private String login;

	private String pwd;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Enumerated(EnumType.STRING)
	private Role role;

	private String image;

	@JsonBackReference(value = "employee_profession")
	@ManyToOne
	@JoinColumn(name = "profession_id")
	@JsonIgnore
	private Profession profession;

	@JsonBackReference(value = "employee_company")
	@ManyToOne
	@JoinColumn(name = "company_id")
	@JsonIgnore
	private Company company;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "employee")
	private List<Post> posts = new ArrayList<>();

	@OneToOne(mappedBy = "employee")
	@JsonIgnore
	private Travel travel;

	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	private List<EventInvitation> eventInvitations = new ArrayList<>();

	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	private List<Favorite> favorites = new ArrayList<>();
	
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	List<Rating> ratings;

	public Employee(Long id) {
		super();
		this.id = id;
	}

}