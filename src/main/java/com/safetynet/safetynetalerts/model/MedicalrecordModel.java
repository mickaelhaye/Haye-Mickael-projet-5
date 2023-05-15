package com.safetynet.safetynetalerts.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Data;

@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class MedicalrecordModel {

	private String firstName;

	private String lastName;

	private String birthdate;

	ArrayList<String> medications = new ArrayList<String>();

	ArrayList<String> allergies = new ArrayList<String>();

}
