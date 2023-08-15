package com.sip.ams1.entities;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Inheritance( strategy = InheritanceType.SINGLE_TABLE )
public  class SuivieDocument {
	@Id
    @GeneratedValue(strategy = GenerationType.TABLE)
	private long NumDoc;
	
	
	
	public SuivieDocument(long numDoc,Nomenclature no) {
		super();
		this.NumDoc = numDoc;
		this.nomenclature=no;
		
	}
	
/**** Many To One ****/
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "nomenclature_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Nomenclature nomenclature;
	
	
	public Nomenclature getNomenclature() {
	return nomenclature;
}
public void setNomenclature(Nomenclature nomenclature) {
	this.nomenclature = nomenclature;}

	public SuivieDocument() {
		super();
	}
	@Override
	public String toString() {
		return "SuivieDocument [NumDoc=" + NumDoc + ", nomenclature=" + nomenclature + "]";
	}
	
	
	
	
}
