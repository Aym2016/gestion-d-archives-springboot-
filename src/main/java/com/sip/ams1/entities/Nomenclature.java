package com.sip.ams1.entities;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

public class Nomenclature {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long CodeNomenclature ;
	private String DesignationNomenClature;
	private String DureeConservation1ereAge;
	private String DureeConservation2emeAge;
	public long getCodeNomenclature() {
		return CodeNomenclature;
	}
	public void setCodeNomenclature(long codeNomenclature) {
		CodeNomenclature = codeNomenclature;
	}
	public String getDesignationNomenClature() {
		return DesignationNomenClature;
	}
	public void setDesignationNomenClature(String designationNomenClature) {
		DesignationNomenClature = designationNomenClature;
	}
	public String getDureeConservation1ereAge() {
		return DureeConservation1ereAge;
	}
	public void setDureeConservation1ereAge(String dureeConservation1ereAge) {
		DureeConservation1ereAge = dureeConservation1ereAge;
	}
	public String getDureeConservation2emeAge() {
		return DureeConservation2emeAge;
	}
	public void setDureeConservation2emeAge(String dureeConservation2emeAge) {
		DureeConservation2emeAge = dureeConservation2emeAge;
	}
	public Nomenclature(int codeNomenclature, String designationNomenClature, String dureeConservation1ereAge,
			String dureeConservation2emeAge) {
		super();
		CodeNomenclature = codeNomenclature;
		DesignationNomenClature = designationNomenClature;
		DureeConservation1ereAge = dureeConservation1ereAge;
		DureeConservation2emeAge = dureeConservation2emeAge;
	}
	public Nomenclature() {
		super();
	}
	
	
/**** Many To One ****/
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "directionRegionale_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private DirectionRegionale directionRegionale;
public DirectionRegionale getDirectionRegionale() {
	return directionRegionale;
}
public void setDirectionRegionale(DirectionRegionale directionRegionale) {
	this.directionRegionale = directionRegionale;
}
@OneToMany(cascade=CascadeType.ALL, mappedBy = "nomenclature")
private List<DemandeVersement> demandeVersement;
public List<DemandeVersement> getDemandeVersement() {
	return demandeVersement;
}
public void setDemandeVersement(List<DemandeVersement> demandeVersement) {
	this.demandeVersement = demandeVersement;
}

	

	
	
	
	
	
	
	
	
	
	
	
}
