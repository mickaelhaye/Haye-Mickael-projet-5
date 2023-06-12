package com.safetynet.safetynetalerts.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe Model Firestation
 * 
 * @author Mickael Hayé
 *
 */
@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class MedicalrecordModel {

	private String firstName;

	private String lastName;

	private String birthdate;

	ArrayList<MedicationModel> medications = new ArrayList<MedicationModel>();

	ArrayList<AllergieModel> allergies = new ArrayList<AllergieModel>();
}
