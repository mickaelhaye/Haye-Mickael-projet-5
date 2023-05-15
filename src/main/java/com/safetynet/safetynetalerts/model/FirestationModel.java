package com.safetynet.safetynetalerts.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Data;

@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class FirestationModel {

	private String address;

	private String station;

}
