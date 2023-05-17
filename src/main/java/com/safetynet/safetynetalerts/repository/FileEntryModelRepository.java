package com.safetynet.safetynetalerts.repository;

import java.util.ArrayList;
import java.util.List;

import com.safetynet.safetynetalerts.model.FirestationModel;
import com.safetynet.safetynetalerts.model.MedicalrecordModel;
import com.safetynet.safetynetalerts.model.PersonModel;
import com.safetynet.safetynetalerts.service.CalculAgeService;
import com.safetynet.safetynetalerts.service.ChildAlertByAddressService;
import com.safetynet.safetynetalerts.service.FoyerbyFirestationService;
import com.safetynet.safetynetalerts.service.PersonByAddressService;
import com.safetynet.safetynetalerts.service.PersonByFoyerService;
import com.safetynet.safetynetalerts.service.PersonbyFirestationService;

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
		List<FirestationModel> listFirestations2 = new ArrayList<FirestationModel>();
		List<PersonbyFirestationService> listPersonByFirestation = new ArrayList<PersonbyFirestationService>();
		List<PersonbyFirestationService> listPersonsPlus18 = new ArrayList<PersonbyFirestationService>();
		List<PersonbyFirestationService> listPersons18EtMoins = new ArrayList<PersonbyFirestationService>();

		for (FirestationModel firestation : firestations) {
			if (firestation.getStation().equals(station)) {
				listFirestations2.add(firestation);
			}
		}
		for (FirestationModel firestation : listFirestations2) {
			for (PersonModel person : persons) {
				if (firestation.getAddress().equals(person.getAddress())) {
					listPersonByFirestation.add(new PersonbyFirestationService(person.getFirstName(),
							person.getLastName(), person.getAddress(), person.getPhone()));
				}
			}
		}
		// Décompte du nombre d'adulte de plus de 18 ans et enfants (individu agé de 18
		// ans ou moins)
		for (PersonbyFirestationService person : listPersonByFirestation) {
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
		for (PersonbyFirestationService personByFirestation : listPersonByFirestation) {
			listObjects.add(personByFirestation);
		}
		listObjects.add("");
		listObjects.add("le nombre d'adultes est de " + listPersonsPlus18.size());
		listObjects.add("");
		listObjects.add("le nombre d'enfants est de " + listPersons18EtMoins.size());

		return listObjects;
	}

	// 2eme request
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

	// 3eme request
	public List<String> findByFirestationAPhone(String station) {
		// Liste des numéros de telephone dépendant d'un numéro de station
		List<String> listPhone = new ArrayList<String>();
		List<FirestationModel> listFirestations2 = new ArrayList<FirestationModel>();

		for (FirestationModel firestation : firestations) {
			if (firestation.getStation().equals(station)) {
				listFirestations2.add(firestation);
			}
		}
		for (FirestationModel firestation : listFirestations2) {
			for (PersonModel person : persons) {
				if (firestation.getAddress().equals(person.getAddress())) {
					if (!listPhone.contains(person.getPhone())) {
						listPhone.add(person.getPhone());
					}
				}
			}
		}

		return listPhone;
	}

	// 4eme request
	public List<Object> findByAddressAPerson(String address) {
		// Liste des personnes habitant à une adresse
		List<Object> listObjects = new ArrayList<Object>();
		List<PersonModel> listPersons2 = new ArrayList<PersonModel>();
		List<PersonModel> listPersonsPlus18 = new ArrayList<PersonModel>();
		List<PersonModel> listPersons18EtMoins = new ArrayList<PersonModel>();
		List<PersonByAddressService> listPersonByAddress = new ArrayList<PersonByAddressService>();
		for (PersonModel person : persons) {
			if (person.getAddress().equals(address)) {
				listPersons2.add(person);
			}
		}

		// recherche dans medicalrecors
		for (PersonModel person : listPersons2) {
			for (MedicalrecordModel medicalrecords : medicalrecords) {
				if ((person.getFirstName().equals(medicalrecords.getFirstName()))
						&& (person.getLastName().equals(medicalrecords.getLastName()))) {
					CalculAgeService calcul = new CalculAgeService();
					int age = calcul.calculAge(medicalrecords.getBirthdate());
					listPersonByAddress.add(new PersonByAddressService(person.getFirstName(), person.getLastName(),
							person.getPhone(), age, medicalrecords.getMedications(), medicalrecords.getAllergies()));
				}
			}
		}

		// recherche numéro de station desservant l'adresse
		String numStation = "";
		for (FirestationModel firestation : firestations) {
			if (firestation.getAddress().equals(address)) {
				numStation = firestation.getStation();
			}
		}

		for (PersonByAddressService personByAdresss : listPersonByAddress) {
			listObjects.add(personByAdresss);
		}

		listObjects.add("");
		listObjects.add("L'adresse " + address + " : est desservie par la station : " + numStation);

		return listObjects;

	}

	// 5eme request
	public List<Object> findByFirestationAFoyer(String station) {
		// Liste des foyer dépendant d'un numéro de station
		List<Object> listObjects = new ArrayList<Object>();
		List<String> listFoyer = new ArrayList<String>();
		List<FirestationModel> listFirestations2 = new ArrayList<FirestationModel>();
		List<FoyerbyFirestationService> listFoyerbyFirestation = new ArrayList<FoyerbyFirestationService>();

		for (FirestationModel firestation : firestations) {
			if (firestation.getStation().equals(station)) {
				listFirestations2.add(firestation);
			}
		}
		for (FirestationModel firestation : listFirestations2) {
			for (PersonModel person : persons) {
				if (firestation.getAddress().equals(person.getAddress())) {
					if (!listFoyer.contains(person.getAddress())) {
						listFoyer.add((person.getAddress()));
					}
				}
			}
		}
		for (String foyer : listFoyer) {
			ArrayList<PersonByFoyerService> listPersonByFoyer = new ArrayList<PersonByFoyerService>();
			boolean personFind = false;
			for (PersonModel person : persons) {
				if (foyer.equals(person.getAddress())) {
					if (!personFind) {
						listFoyerbyFirestation.add(new FoyerbyFirestationService(foyer, null));
						personFind = true;
					}

					for (MedicalrecordModel medicalrecords : medicalrecords) {
						if ((person.getFirstName().equals(medicalrecords.getFirstName()))
								&& (person.getLastName().equals(medicalrecords.getLastName()))) {
							CalculAgeService calcul = new CalculAgeService();
							int age = calcul.calculAge(medicalrecords.getBirthdate());
							String sVal = person.getLastName() + " medications:" + medicalrecords.getMedications()
									+ " allergies:" + medicalrecords.getAllergies();
							listPersonByFoyer
									.add(new PersonByFoyerService(person.getFirstName(), sVal, person.getPhone(), age));
						}
					}
				}

			}
			FoyerbyFirestationService Foyer = listFoyerbyFirestation.get(listFoyerbyFirestation.size() - 1);
			Foyer.setListPersonByFoyer(listPersonByFoyer);
		}

		for (FoyerbyFirestationService foyer : listFoyerbyFirestation) {
			listObjects.add(foyer);
		}

		return listObjects;
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