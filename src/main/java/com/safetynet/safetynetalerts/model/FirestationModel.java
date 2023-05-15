package com.safetynet.safetynetalerts.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class FirestationModel {

	private String address;

	private String station;

	public FirestationModel(String address, String station) {
		super();
		this.address = address;
		this.station = station;
	}

}
