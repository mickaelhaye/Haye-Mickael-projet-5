package com.safetynet.safetynetalerts.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe Model Person
 * 
 * @author Mickael Hayé
 *
 */
@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor

public class PersonModel {
	private String firstName;

	private String lastName;

	private String address;

	private String city;

	private String zip;

	private String phone;

	private String email;

}
