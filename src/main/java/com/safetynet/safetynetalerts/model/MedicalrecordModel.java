package com.safetynet.safetynetalerts.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe Model Firestation
 * 
 * @author Mickael Hayé
 *
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class MedicalrecordModel {

	private String firstName;

	private String lastName;

	private String birthdate;

	ArrayList<String> medications = new ArrayList<String>();

	ArrayList<String> allergies = new ArrayList<String>();

	/**
	 * Constructor
	 * 
	 * @param firstName
	 * @param lastName
	 * @param birthdate
	 * @param medications Liste des médicaments et posologies
	 * @param allergies   Liste des allergies
	 */
	public MedicalrecordModel(String firstName, String lastName, String birthdate, ArrayList<String> medications,
			ArrayList<String> allergies) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.medications = medications;
		this.allergies = allergies;
	}

}
