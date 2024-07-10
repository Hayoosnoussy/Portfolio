package tn.globebusiness.spring.Entities;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.Builder;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String lastName;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    private String email;

    private Long phone;

    @Column(name ="login" , unique = true) 
    private String login;
    
    private String pwd; 

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String image;

    @JsonBackReference(value = "employee_profession")
    @ManyToOne
    @JoinColumn(name = "profession_id")
    private Profession profession;

    @JsonBackReference(value = "employee_company")
    @ManyToOne
    Company company;

    @OneToMany(cascade=CascadeType.ALL,mappedBy="employee")
    private List<Post> posts;

    @OneToOne(mappedBy="employee")
    private Travel travel;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<EventInvitation> eventInvitations = new ArrayList<>();
    
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Favorite> favorites = new ArrayList<>();

	public Employee(Long id) {
		super();
		this.id = id;
	}
}