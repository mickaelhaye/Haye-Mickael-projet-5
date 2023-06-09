package com.safetynet.safetynetalerts.model;

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
public class FirestationModel {

	private String address;

	private String station;

}
