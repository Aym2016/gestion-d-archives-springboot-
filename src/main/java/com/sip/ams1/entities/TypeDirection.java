package com.sip.ams1.entities;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
@Entity
public class TypeDirection {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
    private String CodeTypeDir;
private String LibelleTypeDir;
public String getCodeTypeDir() {
	return CodeTypeDir;
}
public void setCodeTypeDir(String codeTypeDir) {
	CodeTypeDir = codeTypeDir;
}
public String getLibelleTypeDir() {
	return LibelleTypeDir;
}
public void setLibelleTypeDir(String libelleTypeDir) {
	LibelleTypeDir = libelleTypeDir;
}
public TypeDirection(String codeTypeDir, String libelleTypeDir) {
	super();
	CodeTypeDir = codeTypeDir;
	LibelleTypeDir = libelleTypeDir;
}
public TypeDirection() {
	super();
}
@ManyToOne(fetch = FetchType.LAZY, optional = false)
@JoinColumn(name = "directionRegionale_id", nullable = true)
@OnDelete(action = OnDeleteAction.CASCADE)
private DirectionRegionale directionRegionale;
public DirectionRegionale getDirectionRegionale() {
	return directionRegionale;
}


public void setDirectionRegionale(DirectionRegionale directionRegionale) {
	directionRegionale = directionRegionale;
}
@ManyToOne(fetch = FetchType.LAZY, optional = false)
@JoinColumn(name = "role_id", nullable = false)
@OnDelete(action = OnDeleteAction.CASCADE)
private Role role;
public Role getRole() {
	return role;
}
public void setRole(Role role) {
	this.role = role;
} 



}
