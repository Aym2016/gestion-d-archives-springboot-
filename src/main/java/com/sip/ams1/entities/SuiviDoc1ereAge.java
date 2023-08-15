package com.sip.ams1.entities;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table( name = "SuivieDocumentPremiéreAge" )
@Inheritance( strategy = InheritanceType.SINGLE_TABLE )
public class SuiviDoc1ereAge{
	@Id
    @GeneratedValue(strategy = GenerationType.TABLE)
	private long NumDoc;
	public long getNumDoc() {
		return NumDoc;
	}
	public void setNumDoc(long numDoc) {
		NumDoc = numDoc;
	}

	private String ChapitreComptable;
	private int NbrPages;
	private  Date DateCreation ;
	private  Date DateEntree;
	
	
	
	public String getChapitreComptable() {
		return ChapitreComptable;
	}
	public void setChapitreComptable(String chapitreComptable) {
		ChapitreComptable = chapitreComptable;
	}
	public int getNbrPages() {
		return NbrPages;
	}
	public void setNbrPages(int nbrPages) {
		NbrPages = nbrPages;
	}
	public Date getDateCreation() {
		return DateCreation;
	}
	public void setDateCreation1(Date dateCreation) {
		DateCreation = dateCreation;
	}
	public Date getDateEntree() {
		return DateEntree;
	}
	public void setDateEntree(Date dateEntree) {
		DateEntree = dateEntree;
	}
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "nomenclature_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Nomenclature nomenclature;
	
	
	public Nomenclature getNomenclature() {
		return nomenclature;
	}
	public void setNomenclature(Nomenclature nomenclature) {
		this.nomenclature = nomenclature;
	}
	public SuiviDoc1ereAge() {}
	
	public SuiviDoc1ereAge(long numDoc, String chapitreComptable, int nbrPages, Date dateCreation, Date dateEntree,
			DirectionRegionale directionRegionale,Nomenclature no) {
			this.NumDoc=numDoc;
		 this.ChapitreComptable=chapitreComptable;
		  this.NbrPages=nbrPages;
		  this.DateCreation=dateCreation;
		  this.DateEntree=dateEntree;
		  
			
		this.directionRegionale = directionRegionale;
	}
	/*public long getNumDoc1() {
		return super.getNumDoc();
	}
	public void setNumDoc1(long numDoc) {
		super
		.setNumDoc(numDoc);
	}*/
	/*
	public String getChapitreComptable() {
		return super.getChapitreComptable();
	}
	public void setChapitreComptable(String chapitreComptable) {
		super.setChapitreComptable(chapitreComptable);
	}
	public int getNbrPages() {
	return super.getNbrPages();
	}
	public void setNbrPages(int nbrPages) {
		super.setNbrPages(nbrPages);
	}
	public Date getDateCreation() {
	return	super.getDateCreation();
	}
	public void setDateCreation(Date dateCreation) {
		super.setDateCreation(dateCreation);
	}
	public Date getDateEntree() {
		return super.getDateEntree();
	}
	public void setDateEntree(Date dateEntree) {
		super.setDateEntree(dateEntree);
	}	
	*/
	
	

	
	
	


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
@Override
public String toString() {
	return "SuiviDoc1ereAge [directionRegionale=" + directionRegionale + ", toString()=" + super.toString()
			+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
}
public void setDateCreation(Date dateCreation) {
	DateCreation = dateCreation;
}



	
}
