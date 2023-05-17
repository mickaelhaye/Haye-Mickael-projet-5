package com.safetynet.safetynetalerts.service;

import java.util.ArrayList;

import lombok.Data;

@Data
public class FoyerbyFirestationService {
	private String address;

	ArrayList<PersonByFoyerService> listPersonByFoyer = new ArrayList<PersonByFoyerService>();

	public FoyerbyFirestationService(String address, ArrayList<PersonByFoyerService> listPersonnByFoyer) {
		super();
		this.address = address;
		this.listPersonByFoyer = listPersonByFoyer;
	}

}
