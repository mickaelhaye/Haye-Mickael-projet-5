package com.safetynet.safetynetalerts.service;

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
public class PersonbyFirestationService {
	private String firstName;

	private String lastName;

	private String address;

	private String phone;

}
