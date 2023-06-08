package com.safetynet.safetynetalerts.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.safetynetalerts.controller.FirestationController;
import com.safetynet.safetynetalerts.model.FirestationModel;
import com.safetynet.safetynetalerts.model.FoyerbyFirestationModel;
import com.safetynet.safetynetalerts.model.MedicalrecordModel;
import com.safetynet.safetynetalerts.model.PersonByFoyerModel;
import com.safetynet.safetynetalerts.model.PersonModel;
import com.safetynet.safetynetalerts.model.PersonbyFirestationModel;

/**
 * Cette classe permet de traiter les API concernant Firestation
 * 
 * @author Mickael Hayé
 *
 */
@Service
public class FirestationService {
	@Autowired
	private JsonFileReadService jsonFileReadRepository;
	private static Logger logger = LoggerFactory.getLogger(FirestationController.class);

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
		List<PersonbyFirestationModel> listPersonByFirestation = new ArrayList<PersonbyFirestationModel>();
		List<PersonbyFirestationModel> listPersonsPlus18 = new ArrayList<PersonbyFirestationModel>();
		List<PersonbyFirestationModel> listPersons18EtMoins = new ArrayList<PersonbyFirestationModel>();

		// Recupération de la liste des firestations en fonction du numéro de station
		for (FirestationModel firestation : jsonFileReadRepository.getFile().getFirestations()) {
			if (firestation.getStation().equals(station)) {
				listFirestations2.add(firestation);
			}
		}
		// récupération de la liste des personnes en fonction de la liste des
		// firestations
		for (FirestationModel firestation : listFirestations2) {
			for (PersonModel person : jsonFileReadRepository.getFile().getPersons()) {
				if (firestation.getAddress().equals(person.getAddress())) {
					listPersonByFirestation.add(new PersonbyFirestationModel(person.getFirstName(),
							person.getLastName(), person.getAddress(), person.getPhone()));
				}
			}
		}
		// Décompte du nombre d'adulte de plus de 18 ans et enfants (individu agé de 18
		// ans ou moins)
		for (PersonbyFirestationModel person : listPersonByFirestation) {
			for (MedicalrecordModel medicalrecords : jsonFileReadRepository.getFile().getMedicalrecords()) {
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
		for (PersonbyFirestationModel personByFirestation : listPersonByFirestation) {
			listObjects.add(personByFirestation);
		}
		if (!listPersonsPlus18.isEmpty() || !listPersons18EtMoins.isEmpty()) {
			listObjects.add("");
			listObjects.add("le nombre d'adultes est de " + listPersonsPlus18.size());
			listObjects.add("");
			listObjects.add("le nombre d'enfants est de " + listPersons18EtMoins.size());
		}
		return listObjects;
	}

	/**
	 * Traitement de l'API //@GetMapping(value = "/flood/stations/{station}",
	 * récupération des personnes en fonction d'une adresse
	 * 
	 * @param station (station d'entrée)
	 * @return une liste d'objets (Liste de foyers)
	 */
	public List<Object> findByFirestationAFoyer(String[] ListStation) throws Exception {
		logger.debug("findByFirestationAFoyer ");
		// Liste des foyer dépendant d'un numéro de station
		List<Object> listObjects = new ArrayList<Object>();

		for (String station : ListStation) {
			logger.debug("findByFirestationAFoyer " + station);
			List<String> listFoyer = new ArrayList<String>();
			List<FirestationModel> listFirestations2 = new ArrayList<FirestationModel>();
			List<FoyerbyFirestationModel> listFoyerbyFirestation = new ArrayList<FoyerbyFirestationModel>();

			for (FirestationModel firestation : jsonFileReadRepository.getFile().getFirestations()) {
				if (firestation.getStation().equals(station)) {
					listFirestations2.add(firestation);
				}
			}
			for (FirestationModel firestation : listFirestations2) {
				for (PersonModel person : jsonFileReadRepository.getFile().getPersons()) {
					if (firestation.getAddress().equals(person.getAddress())) {
						if (!listFoyer.contains(person.getAddress())) {
							listFoyer.add((person.getAddress()));
						}
					}
				}
			}
			for (String foyer : listFoyer) {
				ArrayList<PersonByFoyerModel> listPersonByFoyer = new ArrayList<PersonByFoyerModel>();
				boolean personFind = false;
				for (PersonModel person : jsonFileReadRepository.getFile().getPersons()) {
					if (foyer.equals(person.getAddress())) {
						if (!personFind) {
							listFoyerbyFirestation.add(new FoyerbyFirestationModel(foyer, null));
							personFind = true;
						}

						for (MedicalrecordModel medicalrecords : jsonFileReadRepository.getFile().getMedicalrecords()) {
							if ((person.getFirstName().equals(medicalrecords.getFirstName()))
									&& (person.getLastName().equals(medicalrecords.getLastName()))) {
								CalculAgeService calcul = new CalculAgeService();
								int age = calcul.calculAge(medicalrecords.getBirthdate());
								String sVal = person.getLastName() + " medications:" + medicalrecords.getMedications()
										+ " allergies:" + medicalrecords.getAllergies();
								listPersonByFoyer.add(
										new PersonByFoyerModel(person.getFirstName(), sVal, person.getPhone(), age));
							}
						}
					}

				}
				FoyerbyFirestationModel Foyer = listFoyerbyFirestation.get(listFoyerbyFirestation.size() - 1);
				Foyer.setListPersonByFoyer(listPersonByFoyer);
			}

			for (FoyerbyFirestationModel foyer : listFoyerbyFirestation) {
				listObjects.add(foyer);
			}
		}

		return listObjects;
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

		// récupération de la liste des firestations en fonction du numéro de station
		for (FirestationModel firestation : jsonFileReadRepository.getFile().getFirestations()) {
			if (firestation.getStation().equals(station)) {
				listFirestations2.add(firestation);
			}
		}
		// récupération de la liste des personnes en fonction de la liste des
		// firestations
		for (FirestationModel firestation : listFirestations2) {
			for (PersonModel person : jsonFileReadRepository.getFile().getPersons()) {
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
	 * //@PostMapping("/firestation")
	 * 
	 * @param firestation
	 * @return un String contenant le résultat du rajout d'une Firestation
	 */
	public String addFirestation(FirestationModel firestation) {
		logger.debug("addFirestation " + firestation);
		jsonFileReadRepository.getFile().getFirestations().add(firestation);
		return "la firestation " + firestation.getAddress() + " Station:" + firestation.getStation() + " a ete ajoutee";
	}

	/**
	 * //@PatchMapping("/firestation")
	 * 
	 * @param firestation
	 * @return un String contenant le résultat de la modification d'une Firestation
	 */

	public String updateFirestation(FirestationModel firestation) {
		logger.debug("updateFirestation " + firestation);
		boolean firestationModifiee = false;
		for (FirestationModel firestationTest : jsonFileReadRepository.getFile().getFirestations()) {
			if (firestationTest.getAddress().equals(firestation.getAddress())) {
				firestationTest.setStation(firestation.getStation());
				firestationModifiee = true;
				break;
			}
		}
		if (!firestationModifiee) {
			return "la firestation " + firestation.getAddress() + " n'est pas reference";
		}
		return "la firestation " + firestation.getAddress() + " a ete modifiee";
	}

	/**
	 * //@DeleteMapping("/firestation/{station}")
	 * 
	 * @param station
	 * @return un String contenant le résultat de la suppression d'une Firestation
	 */
	public String deleteFirestationByStation(String station) {
		logger.debug("deleteFirestationByStation " + station);
		boolean firestationSupprimee = false;
		for (int i = 0; i < jsonFileReadRepository.getFile().getFirestations().size(); i++) {
			FirestationModel firestationTest = jsonFileReadRepository.getFile().getFirestations().get(i);
			if (station.equals(firestationTest.getStation())) {
				jsonFileReadRepository.getFile().getFirestations().remove(firestationTest);
				i--;
				firestationSupprimee = true;
			}
		}
		if (!firestationSupprimee) {
			return "la firestation " + station + " n'est pas reference";
		}
		return "les firestations " + station + " ont ete supprimees";
	}

	/**
	 * //@DeleteMapping("/firestation/{Address}")
	 * 
	 * @param Address
	 * @return un String contenant le résultat de la suppression d'une Firestation
	 */
	public String deleteFirestationByAddress(String address) {
		logger.debug("deleteFirestationByAddress " + address);
		boolean firestationSupprimee = false;
		for (int i = 0; i < jsonFileReadRepository.getFile().getFirestations().size(); i++) {
			FirestationModel firestationTest = jsonFileReadRepository.getFile().getFirestations().get(i);
			if (address.equals(firestationTest.getAddress())) {
				jsonFileReadRepository.getFile().getFirestations().remove(firestationTest);
				firestationSupprimee = true;
				break;
			}
		}
		if (!firestationSupprimee) {
			return "la firestation " + address + " n'est pas reference";
		}
		return "les firestations " + address + " ont ete supprimees";
	}

}
