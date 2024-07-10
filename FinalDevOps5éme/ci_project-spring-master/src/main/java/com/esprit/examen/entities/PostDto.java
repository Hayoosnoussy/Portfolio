package com.esprit.examen.entities;


import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
public class PostDto {

	
	private Long idReglement;
	
	private float montantPaye;

	private float montantRestant;
	
	private Boolean payee;
	
	@Temporal(TemporalType.DATE)
	private Date dateReglement;

	private long idFacture;
	private float montantRemise;
	private float montantFacture;
	@Temporal(TemporalType.DATE)
	private Date dateCreationFacture;
	@Temporal(TemporalType.DATE)
	private Date dateDerniereModificationFacture;
	private Boolean archivee;

	private Long idSecteurActivite;
	private String codeSecteurActivite;
	private String libelleSecteurActivite;
}
