package com.safetynet.safetynetalerts.model;

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
public class PersonbyFirestationModel {
	private String firstName;

	private String lastName;

	private String address;

	private String phone;

}
