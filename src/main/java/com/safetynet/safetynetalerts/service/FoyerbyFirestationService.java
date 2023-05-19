package com.safetynet.safetynetalerts.service;

import java.util.ArrayList;

import lombok.Data;

/**
 * classe permettant de mettre en forme des données pour une API
 * 
 * @author Mickael Hayé
 *
 */
@Data
public class FoyerbyFirestationService {
	private String address;

	ArrayList<PersonByFoyerService> listPersonByFoyer = new ArrayList<PersonByFoyerService>();

	/**
	 * Constructor
	 * 
	 * @param address
	 * @param listPersonnByFoyer
	 */
	public FoyerbyFirestationService(String address, ArrayList<PersonByFoyerService> listPersonnByFoyer) {
		super();
		this.address = address;
		this.listPersonByFoyer = listPersonByFoyer;
	}

}
