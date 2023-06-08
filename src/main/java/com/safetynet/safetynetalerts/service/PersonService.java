package com.safetynet.safetynetalerts.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.safetynetalerts.controller.FirestationController;
import com.safetynet.safetynetalerts.model.ChildAlertByAddressModel;
import com.safetynet.safetynetalerts.model.FirestationModel;
import com.safetynet.safetynetalerts.model.MedicalrecordModel;
import com.safetynet.safetynetalerts.model.PersonByAddressModel;
import com.safetynet.safetynetalerts.model.PersonModel;

/**
 * Cette classe permet de traiter les API concernant Person
 * 
 * @author Mickael Hayé
 *
 */
@Service
public class PersonService {
	@Autowired
	private JsonFileReadService jsonFileReadRepository;

	private static Logger logger = LoggerFactory.getLogger(FirestationController.class);

	/**
	 * traitement de l'API //@GetMapping(value = "/childAlert/{address}"),
	 * récupération des enfants en fonction d'une adresse
	 * 
	 * @param address (address d'entrée)
	 * @return une liste d'enfants
	 */
	public List<ChildAlertByAddressModel> findByAddressAListChild(String address) throws Exception {
		logger.debug("findByAddressAListChild " + address);
		// Liste des personnes habitant à une adresse
		List<PersonModel> listPersons2 = new ArrayList<PersonModel>();
		List<ChildAlertByAddressModel> listChildAlert = new ArrayList<ChildAlertByAddressModel>();
		for (PersonModel person : jsonFileReadRepository.getFile().getPersons()) {
			if (person.getAddress().equals(address)) {
				listPersons2.add(person);
			}
		}

		// Décompte du nombre d'adulte de plus de 18 ans et enfants (individu agé de 18
		// ans ou moins)
		for (PersonModel person : listPersons2) {
			for (MedicalrecordModel medicalrecords : jsonFileReadRepository.getFile().getMedicalrecords()) {
				if ((person.getFirstName().equals(medicalrecords.getFirstName()))
						&& (person.getLastName().equals(medicalrecords.getLastName()))) {
					CalculAgeService calcul = new CalculAgeService();
					int age = calcul.calculAge(medicalrecords.getBirthdate());
					if (age > 18) {
					} else {
						listChildAlert.add(
								new ChildAlertByAddressModel(person.getFirstName(), person.getLastName(), age, null));
					}
				}
			}
		}
		// person dans le même foyer
		for (ChildAlertByAddressModel childAlerte : listChildAlert) {
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
		List<PersonByAddressModel> listPersonByAddress = new ArrayList<PersonByAddressModel>();
		for (PersonModel person : jsonFileReadRepository.getFile().getPersons()) {
			if (person.getAddress().equals(address)) {
				listPersons2.add(person);
			}
		}

		// recherche dans medicalrecors
		for (PersonModel person : listPersons2) {
			for (MedicalrecordModel medicalrecords : jsonFileReadRepository.getFile().getMedicalrecords()) {
				if ((person.getFirstName().equals(medicalrecords.getFirstName()))
						&& (person.getLastName().equals(medicalrecords.getLastName()))) {
					CalculAgeService calcul = new CalculAgeService();
					int age = calcul.calculAge(medicalrecords.getBirthdate());
					listPersonByAddress.add(new PersonByAddressModel(person.getFirstName(), person.getLastName(),
							person.getPhone(), age, medicalrecords.getMedications(), medicalrecords.getAllergies()));
				}
			}
		}

		// recherche numéro de station desservant l'adresse
		String numStation = "";
		for (FirestationModel firestation : jsonFileReadRepository.getFile().getFirestations()) {
			if (firestation.getAddress().equals(address)) {
				numStation = firestation.getStation();
			}
		}

		for (PersonByAddressModel personByAdresss : listPersonByAddress) {
			listObjects.add(personByAdresss);
		}

		if (!listObjects.isEmpty()) {
			listObjects.add("");
			listObjects.add("L'adresse " + address + " : est desservie par la station : " + numStation);
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
	public List<String> findByFirstNameAPerson(String firstName, String lastName) throws Exception {
		logger.debug("findByFirstNameAPerson " + firstName);
		// Liste des personnes en fonction d'un prénom
		List<String> listPerson = new ArrayList<String>();

		for (PersonModel person : jsonFileReadRepository.getFile().getPersons()) {
			if (person.getFirstName().equals(firstName)) {
				if (person.getLastName().equals(lastName)) {
					for (MedicalrecordModel medicalrecords : jsonFileReadRepository.getFile().getMedicalrecords()) {
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
	public List<String> findByCityAEmail(String city) throws Exception {
		logger.debug("findByCityAEmail " + city);
		// Liste des personnes en fonction d'un prénom
		List<String> listEmail = new ArrayList<String>();

		for (PersonModel person : jsonFileReadRepository.getFile().getPersons()) {
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
		jsonFileReadRepository.getFile().getPersons().add(person);
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
		for (PersonModel personTest : jsonFileReadRepository.getFile().getPersons()) {
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
	public String deletePerson(String firstName, String lastName) {
		logger.debug("deletePerson " + firstName + lastName);
		boolean personneSupprimee = false;
		for (PersonModel personTest : jsonFileReadRepository.getFile().getPersons()) {
			String firstNameLastNamePersonTest = personTest.getFirstName() + personTest.getLastName();
			if (firstNameLastNamePersonTest.equals(firstName + lastName)) {
				jsonFileReadRepository.getFile().getPersons().remove(personTest);
				personneSupprimee = true;
				break;
			}
		}
		if (!personneSupprimee) {
			return firstName + " " + lastName + " n'est pas reference";
		}
		return firstName + " " + lastName + " supprime";
	}

}
