package com.sip.ams1.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class StructureCentrale {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long CodeStructure;
	private String LibelleStructure;
	
	public long getCodeStructure() {
		return CodeStructure;
	}
	public void setCodeStructure(long codeStructure) {
		CodeStructure = codeStructure;
	}
	public String getLibelleStructure() {
		return LibelleStructure;
	}
	public void setLibelleStructure(String libelleStructure) {
		LibelleStructure = libelleStructure;
	}
	public StructureCentrale(long codeStructure, String libelleStructure) {
		super();
		CodeStructure = codeStructure;
		LibelleStructure = libelleStructure;
	}
	public StructureCentrale() {
		super();
	}
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "structureCentrale")
    private List<Agence> agence ;

	public List<Agence> getAgence() {
		return agence;
	}
	public void setAgence(List<Agence> agence) {
		this.agence = agence;
	}
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "structureCentrale")
	private List<DemandeConsultation> demandeConsulation ;

	public List<DemandeConsultation> getDemandeConsulation() {
		return demandeConsulation;
	}
	public void setDemandeConsulation(List<DemandeConsultation> demandeConsulation) {
		this.demandeConsulation = demandeConsulation;
	}
	//directionRegionale
/**** Many To One ****/
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "DirectionRegionale_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private DirectionRegionale directionRegionale;

public DirectionRegionale getDirectionRegionale() {
	return directionRegionale;
}
public void setDirectionRegionale(DirectionRegionale directionRegionale) {
	this.directionRegionale = directionRegionale;
}
	

	
	
	

}
