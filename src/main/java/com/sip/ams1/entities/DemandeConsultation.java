package com.sip.ams1.entities;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class DemandeConsultation {
	@Id
    @GeneratedValue(strategy = GenerationType.TABLE)
	private long NumDem;
	private Date DateDem; 
    private String Objet;
    private String NumOrdre;
    private Date DateRetour;
	public long getNumDem() {
		return NumDem;
	}
	public void setNumDem(long numDem) {
		NumDem = numDem;
	}
	public Date getDateDem() {
		return DateDem;
	}
	public void setDateDem(Date dateDem) {
		DateDem = dateDem;
	}
	public String getObjet() {
		return Objet;
	}
	public void setObjet(String objet) {
		Objet = objet;
	}
	public String getNumOrdre() {
		return NumOrdre;
	}
	public void setNumOrdre(String numOrdre) {
		NumOrdre = numOrdre;
	}
	public Date getDateRetour() {
		return DateRetour;
	}
	public void setDateRetour(Date dateRetour) {
		DateRetour = dateRetour;
	}
	public DemandeConsultation(long numDem, Date dateDem, String objet, String numOrdre, Date dateRetour) {
		super();
		NumDem = numDem;
		DateDem = dateDem;
		Objet = objet;
		NumOrdre = numOrdre;
		DateRetour = dateRetour;
	}
	public DemandeConsultation() {
		super();
	}
    
/**** Many To One ****/
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "structureCentrale_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private StructureCentrale structureCentrale;
	
/**** Many To One ****/
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Agence_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Agence agence;
public StructureCentrale getStructureCentrale() {
	return structureCentrale;
}
public void setStructureCentrale(StructureCentrale structureCentrale) {
	this.structureCentrale = structureCentrale;
}
public Agence getAgence() {
	return agence;
}
public void setAgence(Agence agence) {
	this.agence = agence;
}
    
}
