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
public class ChildAlertByAddressService {
	private String firstName;

	private String lastName;

	private int age;

	ArrayList<String> personnDansMemeFoyer = new ArrayList<String>();

	/**
	 * Constructor
	 * 
	 * @param firstName
	 * @param lastName
	 * @param age
	 * @param personnDansMemeFoyer Liste des peronnes dans le même foyer
	 */
	public ChildAlertByAddressService(String firstName, String lastName, int age,
			ArrayList<String> personnDansMemeFoyer) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.personnDansMemeFoyer = personnDansMemeFoyer;

	}

}
