package com.safetynet.safetynetalerts.repository;

import java.util.ArrayList;
import java.util.List;

import com.safetynet.safetynetalerts.model.FirestationModel;
import com.safetynet.safetynetalerts.model.MedicalrecordModel;
import com.safetynet.safetynetalerts.model.PersonModel;
import com.safetynet.safetynetalerts.service.CalculAgeService;
import com.safetynet.safetynetalerts.service.ChildAlertByAddressService;

import lombok.Data;

@Data
public class FileEntryModelRepository {

	List<PersonModel> persons;
	List<FirestationModel> firestations;
	List<MedicalrecordModel> medicalrecords;

	// 1ere request
	public List<Object> findByFirestationAListPersons(String station) {
		// Liste des personnes dépendant d'un numéro de station
		List<Object> listObjects = new ArrayList<Object>();
		List<PersonModel> listPersons2 = new ArrayList<PersonModel>();
		List<FirestationModel> listFirestations2 = new ArrayList<FirestationModel>();
		List<PersonModel> listPersonsPlus18 = new ArrayList<PersonModel>();
		List<PersonModel> listPersons18EtMoins = new ArrayList<PersonModel>();
		for (FirestationModel firestation : firestations) {
			if (firestation.getStation().equals(station)) {
				listFirestations2.add(firestation);
			}
		}
		for (FirestationModel firestation : listFirestations2) {
			for (PersonModel person : persons) {
				if (firestation.getAddress().equals(person.getAddress())) {
					listPersons2.add(person);
				}
			}
		}
		// Décompte du nombre d'adulte de plus de 18 ans et enfants (individu agé de 18
		// ans ou moins)
		for (PersonModel person : listPersons2) {
			for (MedicalrecordModel medicalrecords : medicalrecords) {
				if ((person.getFirstName().equals(medicalrecords.getFirstName()))
						&& (person.getLastName().equals(medicalrecords.getLastName()))) {
					CalculAgeService calcul = new CalculAgeService();
					int age = calcul.calculAge(medicalrecords.getBirthdate());
					if (age > 18) {
						listPersonsPlus18.add(person);
					} else {
						listPersons18EtMoins.add(person);
					}
				}
			}
		}
		listObjects.add(listPersons2);
		listObjects.add("");
		listObjects.add("le nombre d'adultes est de " + listPersonsPlus18.size());
		listObjects.add("");
		listObjects.add("le nombre d'enfants est de " + listPersons18EtMoins.size());

		return listObjects;
	}

	public List<ChildAlertByAddressService> findByAddressAListChild(String address) {
		// Liste des personnes habitant à une adresse
		List<PersonModel> listPersons2 = new ArrayList<PersonModel>();
		List<PersonModel> listPersonsPlus18 = new ArrayList<PersonModel>();
		List<PersonModel> listPersons18EtMoins = new ArrayList<PersonModel>();
		List<ChildAlertByAddressService> listChildAlert = new ArrayList<ChildAlertByAddressService>();
		for (PersonModel person : persons) {
			if (person.getAddress().equals(address)) {
				listPersons2.add(person);
			}
		}

		// Décompte du nombre d'adulte de plus de 18 ans et enfants (individu agé de 18
		// ans ou moins)
		for (PersonModel person : listPersons2) {
			for (MedicalrecordModel medicalrecords : medicalrecords) {
				if ((person.getFirstName().equals(medicalrecords.getFirstName()))
						&& (person.getLastName().equals(medicalrecords.getLastName()))) {
					CalculAgeService calcul = new CalculAgeService();
					int age = calcul.calculAge(medicalrecords.getBirthdate());
					if (age > 18) {
					} else {
						listChildAlert.add(
								new ChildAlertByAddressService(person.getFirstName(), person.getLastName(), age, null));
					}
				}
			}
		}
		// person dans le même foyer
		for (ChildAlertByAddressService childAlerte : listChildAlert) {
			ArrayList<String> personnDansMemeFoyer = new ArrayList<String>();
			for (PersonModel person : listPersons2) {
				if (!person.getFirstName().equals(childAlerte.getFirstName())) {
					personnDansMemeFoyer.add(person.getFirstName() + " " + person.getLastName());
				}

			}
			childAlerte.setPersonnDansMemeFoyer(personnDansMemeFoyer);
		}
		return listChildAlert;
	}

	public String addPerson(PersonModel person) {
		persons.add(person);
		return person.getFirstName() + " " + person.getLastName() + " ajouté";
	}

	public String updatePerson(PersonModel person) {
		boolean personneModifiee = false;
		for (PersonModel personTest : persons) {
			if ((personTest.getFirstName().equals(person.getFirstName()))
					&& (personTest.getLastName().equals(person.getLastName()))) {
				personTest.setAddress(person.getAddress());
				personTest.setCity(person.getCity());
				personTest.setZip(person.getZip());
				personTest.setPhone(person.getPhone());
				personTest.setEmail(person.getEmail());
				personneModifiee = true;
				break;
			}
		}
		if (!personneModifiee) {
			return person.getFirstName() + " " + person.getLastName() + " n'est pas référencé";
		}
		return person.getFirstName() + " " + person.getLastName() + " modifié";
	}

	public String deletePerson(String firstNameLastName) {
		boolean personneSupprimee = false;
		for (PersonModel personTest : persons) {
			String firstNameLastNamePersonTest = personTest.getFirstName() + personTest.getLastName();
			if (firstNameLastNamePersonTest.equals(firstNameLastName)) {
				persons.remove(personTest);
				personneSupprimee = true;
				break;
			}
		}
		if (!personneSupprimee) {
			return firstNameLastName + " n'est pas référencé";
		}
		return firstNameLastName + " supprimé";
	}

}