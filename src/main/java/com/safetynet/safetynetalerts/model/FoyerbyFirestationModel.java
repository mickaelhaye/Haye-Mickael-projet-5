package com.safetynet.safetynetalerts.model;

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
public class FoyerbyFirestationModel {
	private String address;

	ArrayList<PersonByFoyerModel> listPersonByFoyer = new ArrayList<PersonByFoyerModel>();
}
