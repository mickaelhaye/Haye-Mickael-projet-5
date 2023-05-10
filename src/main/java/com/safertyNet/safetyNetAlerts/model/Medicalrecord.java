package com.safertyNet.safetyNetAlerts.model;

import java.util.ArrayList;

import lombok.Data;

@Data
public class Medicalrecord {

	private String firstName;

	private String lastName;

	private String birthdate;

	ArrayList<String> medications = new ArrayList<String>();

	ArrayList<String> allergies = new ArrayList<String>();

	public Medicalrecord(String firstName, String lastName, String birthdate, ArrayList<String> medications,
			ArrayList<String> allergies) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.medications = medications;
		this.allergies = allergies;
	}

	@Override
	public String toString() {
		return "Medicalrecord [firstName=" + firstName + ", lastName=" + lastName + ", birthdate=" + birthdate
				+ ", medications=" + medications + ", allergies=" + allergies + "]";
	}

}
