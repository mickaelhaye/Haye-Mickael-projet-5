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
public class PersonByFoyerModel {

	private String firstName;

	private String lastName_medications_allergies;

	private String phone;

	private int age;

}
