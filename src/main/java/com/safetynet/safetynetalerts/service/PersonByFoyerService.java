package com.safetynet.safetynetalerts.service;

import lombok.Data;

/**
 * classe permettant de mettre en forme des données pour une API
 * 
 * @author Mickael Hayé
 *
 */
@Data
public class PersonByFoyerService {

	private String firstName;

	private String lastName_medications_allergies;

	private String phone;

	private int age;

	/**
	 * Constructor
	 * 
	 * @param firstName
	 * @param lastName_medications_allergies
	 * @param phone
	 * @param age
	 */
	public PersonByFoyerService(String firstName, String lastName_medications_allergies, String phone, int age) {
		super();
		this.firstName = firstName;
		this.lastName_medications_allergies = lastName_medications_allergies;
		this.phone = phone;
		this.age = age;
	}

}
