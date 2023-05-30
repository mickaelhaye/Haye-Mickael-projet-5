package com.safetynet.safetynetalerts.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.safetynet.safetynetalerts.controller.FirestationController;
import com.safetynet.safetynetalerts.model.FirestationModel;
import com.safetynet.safetynetalerts.model.MedicalrecordModel;
import com.safetynet.safetynetalerts.model.PersonModel;

import lombok.Data;

/**
 * Cette classe permet de traiter les API concernant Person
 * 
 * @author Mickael Hayé
 *
 */
@Data
@Service
public class PersonService {
	private static Logger logger = LoggerFactory.getLogger(FirestationController.class);
	private List<PersonModel> persons;
	private List<FirestationModel> firestations;
	private List<MedicalrecordModel> medicalrecords;

	/**
	 * traitement de l'API //@GetMapping(value = "/firestation/{station}"),
	 * récupération des personnes couvertes par une station
	 * 
	 * @param station (station d'entrée)
	 * @return une liste d'Objets (liste de persons + décompte adultes +décompte
	 *         enfants
	 */
	public List<Object> findByFirestationAListPersons(String station) throws Exception {
		logger.debug("findByFirestationAListPersons " + station);
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

	/**
	 * traitement de l'API //@GetMapping(value = "/childAlert/{address}"),
	 * récupération des enfants en fonction d'une adresse
	 * 
	 * @param address (address d'entrée)
	 * @return une liste d'enfants
	 */
	public List<ChildAlertByAddressService> findByAddressAListChild(String address) throws Exception {
		logger.debug("findByAddressAListChild " + address);
		// Liste des personnes habitant à une adresse
		List<PersonModel> listPersons2 = new ArrayList<PersonModel>();
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

	/**
	 * traitement de l'API //@GetMapping(value = "/phoneAlert/{station}"),
	 * Récupération des numéros de téléphone desservis par la caserne
	 * 
	 * @param station (station d'entrée)
	 * @return une liste de numéros de téléphone
	 */
	public List<String> findByFirestationAPhone(String station) throws Exception {
		logger.debug("findByFirestationAPhone " + station);
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

	/**
	 * traitement de l'API //@GetMapping(value = "/fire/{address}"), récupération
	 * des personnes en fonction d'une adresse
	 * 
	 * @param address (adress d'entrée)
	 * @return une liste d'objets (Liste de persons + numéro de station)
	 */
	public List<Object> findByAddressAPerson(String address) throws Exception {
		logger.debug("findByAddressAPerson " + address);
		// Liste des personnes habitant à une adresse
		List<Object> listObjects = new ArrayList<Object>();
		List<PersonModel> listPersons2 = new ArrayList<PersonModel>();
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

	/**
	 * Traitement de l'API //@GetMapping(value = "/flood/stations/{station}",
	 * récupération des personnes en fonction d'une adresse
	 * 
	 * @param station (station d'entrée)
	 * @return une liste d'objets (Liste de foyers)
	 */
	/*
	 * Récupération des personnes en fonction d'une adresse
	 * 
	 * @GetMapping(value = "/flood/stations/{station}")
	 */
	public List<Object> findByFirestationAFoyer(String station) throws Exception {
		logger.debug("findByFirestationAFoyer " + station);
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

	/**
	 * Traitement de l'API //@GetMapping(value = "/personInfo/{firstName}"),
	 * récupération des personnes en fonction d'un prénom
	 * 
	 * @param firstName (prénom d'entrée)
	 * @return une liste de persons
	 */
	/*
	 * Récupération des personnes en fonction d'un prénom
	 * 
	 * @GetMapping(value = "/personInfo/{firstName}")
	 */
	public List<String> findByFirstNameAPerson(String firstName) throws Exception {
		logger.debug("findByFirstNameAPerson " + firstName);
		// Liste des personnes en fonction d'un prénom
		List<String> listPerson = new ArrayList<String>();

		for (PersonModel person : persons) {
			if (person.getFirstName().equals(firstName)) {
				for (MedicalrecordModel medicalrecords : medicalrecords) {
					if ((person.getFirstName().equals(medicalrecords.getFirstName()))
							&& (person.getLastName().equals(medicalrecords.getLastName()))) {
						CalculAgeService calcul = new CalculAgeService();
						int age = calcul.calculAge(medicalrecords.getBirthdate());
						listPerson.add(person.getLastName() + " , " + person.getAddress() + " , " + age + " , "
								+ person.getEmail() + " , " + medicalrecords.getMedications() + " , "
								+ medicalrecords.getAllergies());

					}
				}
			}
		}
		return listPerson;
	}

	/**
	 * Traitement de l''API //@GetMapping(value = "/communityEmail/{city}"),
	 * récupération des adresses mail en fonction d'une ville
	 * 
	 * @param city (city d'entrée)
	 * @return une liste d'emails
	 */
	/*
	 * Récupération des adresses mail en fonction d'une ville
	 * 
	 * @GetMapping(value = "/communityEmail/{city}")
	 */
	public List<String> findByCityAEmail(String city) throws Exception {
		logger.debug("findByCityAEmail " + city);
		// Liste des personnes en fonction d'un prénom
		List<String> listEmail = new ArrayList<String>();

		for (PersonModel person : persons) {
			if (person.getCity().equals(city)) {
				listEmail.add(person.getEmail());
			}
		}
		return listEmail;
	}

	/**
	 * //@PostMapping("/person")
	 * 
	 * @param person
	 * @return un String contenant le résultat du rajout d'une person
	 */
	public String addPerson(PersonModel person) {
		logger.debug("addPerson " + person);
		for (PersonModel personTest : persons) {
			if ((personTest.getFirstName().equals(person.getFirstName()))
					&& (personTest.getLastName().equals(person.getLastName()))) {
				return person.getFirstName() + " " + person.getLastName() + " deja present";
			}
		}
		persons.add(person);
		return person.getFirstName() + " " + person.getLastName() + " ajoute";
	}

	/**
	 * //@PatchMapping("/person")
	 * 
	 * @param person
	 * @return un String contenant le résultat de la modification d'une person
	 */
	public String updatePerson(PersonModel person) {
		logger.debug("updatePerson " + person);
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
			return person.getFirstName() + " " + person.getLastName() + " n'est pas reference";
		}
		return person.getFirstName() + " " + person.getLastName() + " modifie";
	}

	/**
	 * //@DeleteMapping("/person")
	 * 
	 * @param firstNameLastName
	 * @return un String contenant le résultat de la suppression d'une person
	 */
	public String deletePerson(String firstNameLastName) {
		logger.debug("deletePerson " + firstNameLastName);
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
			return firstNameLastName + " n'est pas reference";
		}
		return firstNameLastName + " supprime";
	}

}
