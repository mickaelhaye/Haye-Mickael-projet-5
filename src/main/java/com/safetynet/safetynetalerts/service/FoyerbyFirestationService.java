package com.safetynet.safetynetalerts.service;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * classe permettant de mettre en forme des données pour une API
 * 
 * @author Mickael Hayé
 *
 */
@Data
@AllArgsConstructor
public class FoyerbyFirestationService {
	private String address;

	ArrayList<PersonByFoyerService> listPersonByFoyer = new ArrayList<PersonByFoyerService>();
}
