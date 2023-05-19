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
public class PersonByAddressService {

	private String firstName;

	private String lastName;

	private String phone;

	private int age;

	ArrayList<String> medications = new ArrayList<String>();

	ArrayList<String> allergies = new ArrayList<String>();

	/**
	 * Constructor
	 * 
	 * @param firstName
	 * @param lastName
	 * @param phone
	 * @param age
	 * @param medications
	 * @param allergies
	 */
	public PersonByAddressService(String firstName, String lastName, String phone, int age,
			ArrayList<String> medications, ArrayList<String> allergies) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.age = age;
		this.medications = medications;
		this.allergies = allergies;
	}

}
