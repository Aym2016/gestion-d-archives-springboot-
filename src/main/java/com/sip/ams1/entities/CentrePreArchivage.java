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
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "CentrePreArchivage")
public class CentrePreArchivage {
	@Id
    @GeneratedValue(strategy= GenerationType.TABLE)
   	private long CodeCentre;
    private String LibelleCentre;
	
    public long getCodeCentre() {
		return CodeCentre;
	}
	public void setCodeCentre(long codeCentre) {
		CodeCentre = codeCentre;
	}
	public String getLibelleCentre() {
		return LibelleCentre;
	}
	public void setLibelleCentre(String libelleCentre) {
		LibelleCentre = libelleCentre;
	}
	public CentrePreArchivage(int codeCentre, String libelleCentre) {
		super();
		CodeCentre = codeCentre;
		LibelleCentre = libelleCentre;
	}
	public CentrePreArchivage() {
		super();
	}
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "centrePreArchivage")
    private List<SuiviDoc2emeAge> docAge2;

	public List<SuiviDoc2emeAge> getDocAge2() {
		return docAge2;
	}
	public void setDocAge2(List<SuiviDoc2emeAge> docAge2) {
		this.docAge2 = docAge2;
	}
	
	
    
    
    

}
