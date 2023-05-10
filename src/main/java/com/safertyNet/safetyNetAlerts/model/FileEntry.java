package com.safertyNet.safetyNetAlerts.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class FileEntry {

	Person person;

	List<Person> listPersons = new ArrayList<Person>();
	List<Firestation> listFirestations = new ArrayList<Firestation>();
	List<Medicalrecord> listMedicalrecords = new ArrayList<Medicalrecord>();

	public void addPerson(String firstName, String lastName, String address, String city, String zip, String phone,
			String email) {
		Person person = new Person(firstName, lastName, address, city, zip, phone, email);
		listPersons.add(person);
	}

	public void addFirestation(String address, String station) {
		Firestation firestation = new Firestation(address, station);
		listFirestations.add(firestation);
	}

	public void addMedicalrecord(String firstName, String lastName, String birthdate, ArrayList<String> medications,
			ArrayList<String> allergies) {
		Medicalrecord medicalrecord = new Medicalrecord(firstName, lastName, birthdate, medications, allergies);
		listMedicalrecords.add(medicalrecord);
	}
}