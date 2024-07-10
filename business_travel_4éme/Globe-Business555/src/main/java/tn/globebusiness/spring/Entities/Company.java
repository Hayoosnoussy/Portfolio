package tn.globebusiness.spring.Entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    @Column(unique = true)
    private String email;

    private Long phone;

    private String image;
    
    @Column(name ="login" , unique = true)
    private String login;
    
    private String pwd; 
    
    @Enumerated(EnumType.STRING)
    private Role role;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy="company")
	private Set<Invitation> invitations;

    @ManyToOne
    @JoinColumn(name = "domain_id")
    @JsonIgnore
    private Domain domain;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Employee> employees = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Event> events;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<EventInvitation> eventInvitations;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Profession> professions = new ArrayList<>();
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy="company")
	private List<Travel> travels;

}