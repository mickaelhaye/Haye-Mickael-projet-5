package com.safetynet.safetynetalerts.service;

import lombok.Data;

/**
 * classe permettant de mettre en forme des données pour une API
 * 
 * @author Mickael Hayé
 *
 */
@Data
public class PersonbyFirestationService {
	private String firstName;

	private String lastName;

	private String address;

	private String phone;

	/**
	 * Constructor
	 * 
	 * @param firstName
	 * @param lastName
	 * @param address
	 * @param phone
	 */
	public PersonbyFirestationService(String firstName, String lastName, String address, String phone) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
	}

}
