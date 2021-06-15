package org.sid.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("IR")
public class IR extends Taxe {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IR() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IR(String titre, Date dateTaxe, double montant, Entreprise entreprise) {
		super(titre, dateTaxe, montant, entreprise);
		// TODO Auto-generated constructor stub
	}

}
