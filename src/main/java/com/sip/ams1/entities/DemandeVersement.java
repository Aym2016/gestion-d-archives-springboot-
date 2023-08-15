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

public class DemandeVersement {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long NumDem;
	private Date DateDem; 
    private String Objet;
    private int NbrCarton;
    private String Etat;
	   
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
public int getNbrCarton() {
		return NbrCarton;
	}
public void setNbrCarton(int nbrCarton) {
		NbrCarton = nbrCarton;
	}
public  String getEtat() {
		return Etat;
	}
public void setEtat(String etat) {
		Etat = etat;
	}
	
    

	public DemandeVersement(long numDem, Date dateDem, String objet, int nbrCarton, String etat) {
	super();
	NumDem = numDem;
	DateDem = dateDem;
	Objet = objet;
	NbrCarton = nbrCarton;
	Etat = etat;
}

	public DemandeVersement() {
		super();
	}

	@Override
	public String toString() {
		return "DemandeVersement [NumDem=" + NumDem + ", DateDem=" + DateDem + ", Objet=" + Objet + ", NbrCarton="
				+ NbrCarton + ", Etat=" + Etat + "]";
	}
	
/**** Many To One ****/
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LieuArchive_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private LieuArchive lieuArchive;

/**** Many To One ****/
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "DirectionRegionale_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private DirectionRegionale directionRegionale;
	
	
	
/**** Many To One ****/
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "nomenclature_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Nomenclature nomenclature;

public LieuArchive getLieuArchive() {
	return lieuArchive;
}

public void setLieuArchive(LieuArchive lieuArchive) {
	this.lieuArchive = lieuArchive;
}

public DirectionRegionale getDirectionRegionale() {
	return directionRegionale;
}

public void setDirectionRegionale(DirectionRegionale directionRegionale) {
	this.directionRegionale = directionRegionale;
}

public Nomenclature getNomenclature() {
	return nomenclature;
}

public void setNomenclature(Nomenclature nomenclature) {
	this.nomenclature = nomenclature;
}



}
