package com.safertyNet.safetyNetAlerts.model;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class Medicalrecord {

	private String firstName;
	
	private String lastName;
	
	List<String> medications = new ArrayList<String>();
	
	List<String> allergies = new ArrayList<String>();

	public Medicalrecord(String firstName, String lastName, List<String> medications, List<String> allergies) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.medications = medications;
		this.allergies = allergies;
	}

	@Override
	public String toString() {
		return "Medicalrecord [firstName=" + firstName + ", lastName=" + lastName + ", medications=" + medications
				+ ", allergies=" + allergies + "]";
	}
	
	

}
