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
public class PersonByAddressModel {

	private String firstName;

	private String lastName;

	private String phone;

	private int age;

	ArrayList<MedicationModel> medications = new ArrayList<MedicationModel>();

	ArrayList<AllergieModel> allergies = new ArrayList<AllergieModel>();
}
