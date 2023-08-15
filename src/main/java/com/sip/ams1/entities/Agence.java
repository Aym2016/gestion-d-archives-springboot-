package com.sip.ams1.entities;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import com.sip.ams1.entities.StructureCentrale;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Agence {
@Id
@GeneratedValue(generator="system-uuid")
@GenericGenerator(name="system-uuid", strategy = "uuid")
private String CodeAgence;
private String LibelleAgence;

public String getCodeAgence() {
	return CodeAgence;
}
public void setCodeAgence(String codeAgence) {
	CodeAgence = codeAgence;
}
public String getLibelleAgence() {
	return LibelleAgence;
}
public void setLibelleAgence(String libelleAgence) {
	LibelleAgence = libelleAgence;
}






public Agence(String codeAgence, String libelleAgence) {
	super();
	CodeAgence = codeAgence;
	LibelleAgence = libelleAgence;
}
public Agence() {

}

@ManyToOne(fetch = FetchType.LAZY, optional = false)
@JoinColumn(name = "structureCentrale_id", nullable = false)
@OnDelete(action = OnDeleteAction.CASCADE)
private StructureCentrale structureCentrale ;




public StructureCentrale getStructureCentrale() {
	return structureCentrale;
}
public void setStructureCentrale(StructureCentrale structureCentrale) {
	this.structureCentrale = structureCentrale;
}


@OneToMany(cascade=CascadeType.ALL, mappedBy = "agence")
private List<DemandeConsultation> demandeConsultation;



}
