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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity

public class LieuArchive {
@Id
@GeneratedValue(strategy = GenerationType.TABLE)
private long CodeLieu;
private String LibelleLieu;
public long getCodeLieu() {
	return CodeLieu;
}
public void setCodeLieu(long codeLieu) {
	CodeLieu = codeLieu;
}
public String getLibelleLieu() {
	return LibelleLieu;
}
public void setLibelleLieu(String libelleLieu) {
	LibelleLieu = libelleLieu;
}
public LieuArchive(int codeLieu, String libelleLieu) {
	super();
	CodeLieu = codeLieu;
	LibelleLieu = libelleLieu;
}
public LieuArchive() {
	super();
	
}

@OneToMany(cascade=CascadeType.ALL, mappedBy = "lieuArchive")
private List<DemandeVersement> demandeVersement ;




}
