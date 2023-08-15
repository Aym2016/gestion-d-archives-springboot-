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
@Table( name = "SuiviDocumentDeuxiémeAge" )
@Inheritance( strategy = InheritanceType.SINGLE_TABLE )
public class SuiviDoc2emeAge{
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
	public void setDateCreation(Date dateCreation) {
		DateCreation = dateCreation;
	}
	public Date getDateEntree() {
		return DateEntree;
	}
	public void setDateEntree(Date dateEntree) {
		DateEntree = dateEntree;
	}


/*public SuiviDoc2emeAge(long numDoc, String chapitreComptable, int nbrPages, Date dateCreation, Date dateEntree,
			CentrePreArchivage centrePreArchivage) {
		super(numDoc, chapitreComptable, nbrPages, dateCreation, dateEntree);
		this.centrePreArchivage = centrePreArchivage;
	}*/






/**** Many To One ****/
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "centrePreArchivage_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CentrePreArchivage centrePreArchivage;


	
	public CentrePreArchivage getCentrePreArchivage() {
	return centrePreArchivage;
}



public void setCentrePreArchivage(CentrePreArchivage centrePreArchivage) {
	this.centrePreArchivage = centrePreArchivage;
}

    public SuiviDoc2emeAge() {
    	
    }

	public SuiviDoc2emeAge(long numDoc, String chapitreComptable, int nbrPages, Date dateCreation, Date dateEntree,
		CentrePreArchivage centrePreArchivage,Nomenclature no) {
	 this.NumDoc=numDoc;
	this.nomenclature=no;
	 this.ChapitreComptable=chapitreComptable;
	  this.NbrPages=nbrPages;
	  this.DateCreation=dateCreation;
	  this.DateEntree=dateEntree;
	  
		
	this.centrePreArchivage = centrePreArchivage;
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


	/*@Override
	public String toString() {
		return "SuiviDoc2emeAge [centrePreArchivage=" + centrePreArchivage + ", getNumDoc()=" + getNumDoc()
				+ ", getChapitreComptable()=" + getChapitreComptable() + ", getNbrPages()=" + getNbrPages()
				+ ", getDateCreation()=" + getDateCreation() + ", getDateEntree()=" + getDateEntree() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}*/

}
