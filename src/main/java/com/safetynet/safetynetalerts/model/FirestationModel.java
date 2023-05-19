package com.safetynet.safetynetalerts.model;

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
public class FirestationModel {

	private String address;

	private String station;

	/**
	 * Constructor
	 * 
	 * @param address adresse de la station
	 * @param station numéro de la station
	 */
	public FirestationModel(String address, String station) {
		super();
		this.address = address;
		this.station = station;
	}

}
