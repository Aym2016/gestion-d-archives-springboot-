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
import com.sip.ams1.entities.TypeDirection;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class DirectionRegionale {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String CodeDirection;
	private String LibelleDirection;
	public String getCodeDirection() {
		return CodeDirection;
	}
	public void setCodeDirection(String codeDirection) {
		CodeDirection = codeDirection;
	}
	public String getLibelleDirection() {
		return LibelleDirection;
	}
	public void setLibelleDirection(String libelleDirection) {
		LibelleDirection = libelleDirection;
	}
	
	public DirectionRegionale(String codeDirection, String libelleDirection) {
		super();
		CodeDirection = codeDirection;
		LibelleDirection = libelleDirection;
	}
	public DirectionRegionale() {
		super();
	}
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "directionRegionale")
    private List<TypeDirection> typeDirection;
	public List<TypeDirection> getTypeDirection() {
		return typeDirection;
	}
	public void setTypeDirection(List<TypeDirection> typeDirection) {
		this.typeDirection = typeDirection;
	}
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "directionRegionale")
    private List<SuiviDoc1ereAge> docAge1;
	
	
	public List<SuiviDoc1ereAge> getDocAge1() {
		return docAge1;
	}
	public void setDocAge1(List<SuiviDoc1ereAge> docAge1) {
		this.docAge1 = docAge1;
	}
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "directionRegionale")
    private List<Nomenclature> nomenclature;
	
	public List<Nomenclature> getNomenclature() {
		return nomenclature;
	}
	public void setNomenclature(List<Nomenclature> nomenclature) {
		this.nomenclature = nomenclature;
	}
	
/**** Many To One ****/
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LieuArchive_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private LieuArchive lieuArchive;
	
	

public LieuArchive getLieuArchive() {
	return lieuArchive;
}
public void setLieuArchive(LieuArchive lieuArchive) {
	this.lieuArchive = lieuArchive;
}


	
	
}
