package com.sip.ams1.entities;


import java.util.List;

//import lombok.Data;
import javax.persistence.*; 
//@Data
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private int id;
    @Column(name = "role")
    private String role;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	} 
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "role")
	private List<TypeDirection> typeDirection ;
}
