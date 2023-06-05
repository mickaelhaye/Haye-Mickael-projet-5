package com.safetynet.safetynetalerts.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.safetynetalerts.model.FileEntryModel;
import com.safetynet.safetynetalerts.model.FirestationModel;
import com.safetynet.safetynetalerts.service.FirestationService;
import com.safetynet.safetynetalerts.service.JsonFileReadService;

/**
 * Cette classe gère les API au niveau de Firestation
 * 
 * @author Mickael Hayé
 */
@RestController
public class FirestationController {

	private static Logger logger = LoggerFactory.getLogger(FirestationController.class);

	@Autowired
	private JsonFileReadService jsonFileReadService;

	@Autowired
	private FirestationService FirestationService;

	@Autowired
	private FirestationService firestationService;

	private FileEntryModel file;

	/**
	 * API pour récupérer des personnes couvertes par une station
	 * 
	 * @param station (station d'entrée)
	 * @return une liste d'Objets (liste de persons + décompte adultes +décompte
	 *         enfants
	 * @throws Exception mauvais retour personService.findByFirestationAListPersons
	 */
	@GetMapping("/firestation")
	public List<Object> afficherUneListePersonne(
			@RequestParam(required = false, name = "stationNumber", defaultValue = "0") String station_number)
			throws Exception {
		if (!station_number.equals("0")) {
			List<Object> list = FirestationService.findByFirestationAListPersons(station_number);
			logger.info("Liste des personnes couvertes par la caserne de pompier correspondante " + list);
			return list;
		}
		file = jsonFileReadService.getFile();
		logger.info("Récupération de la liste des firestations");
		List<Object> list = new ArrayList<Object>();
		for (FirestationModel firestation : file.getFirestations()) {
			list.add(firestation);
		}
		return list;
	}

	/**
	 * Pour récupérer des personnes en fonction d'une adresse
	 * 
	 * @param station (station d'entrée)
	 * @return une liste d'objets (Liste de foyers)
	 * @throws Exception mauvais retour
	 *                   personService.findByFirestationAFoyer(station)
	 */
	@GetMapping("/flood/stations")
	public List<Object> afficherUneListeFoyerParFirestation(
			@RequestParam(name = "stations", defaultValue = "0") String[] ListStation) throws Exception {
		List<Object> list = FirestationService.findByFirestationAFoyer(ListStation);
		logger.info("Liste des personnes en fonction d'une adresse " + list);
		return list;
	}

	/**
	 * Pour récupérer des numéros de téléphone desservis par la caserne
	 * 
	 * @param station (station d'entrée)
	 * @return une liste de numéros de téléphone
	 * @throws Exception mauvais retour
	 *                   personService.findByFirestationAPhone(station)
	 */
	@GetMapping("/phoneAlert")
	public List<String> afficherUneListeNumTelephone(
			@RequestParam(name = "firestation", defaultValue = "0") String firestation_number) throws Exception {
		List<String> list = FirestationService.findByFirestationAPhone(firestation_number);
		logger.info("Liste des numéros de téléphon,e desservis par une caserne " + list);
		return list;
	}

	/**
	 * API pour rajouter un firestation
	 * 
	 * @param firestation
	 * @throws Exception écriture fichier érroné
	 */
	@PostMapping("/firestation")
	public String ajouterFirestation(@RequestBody FirestationModel firestation) throws Exception {
		String sVal = firestationService.addFirestation(firestation);
		logger.info("Rajout d'une firestation " + sVal);
		return sVal;
	}

	/**
	 * API pour modifier un firestation
	 * 
	 * @param firestation
	 * @throws Exception écriture fichier érroné
	 */
	@PatchMapping("/firestation")
	public String mettreAJourFirestation(@RequestBody FirestationModel firestation) throws Exception {
		String sVal = firestationService.updateFirestation(firestation);
		logger.info("Modification d'une firestation " + sVal);
		return sVal;

	}

	/**
	 * API pour supprimer un firestation
	 * 
	 * @param station
	 * @throws Exception écriture fichier érroné
	 */
	@DeleteMapping("/firestation")
	public String supprimerFirestationByStation(@RequestParam(required = false, name = "station") String station,
			@RequestParam(required = false, name = "address") String address) throws Exception {
		String sVal = "";
		if (station != null) {
			sVal = firestationService.deleteFirestationByStation(station);
		} else {
			if (address != null) {
				sVal = firestationService.deleteFirestationByAddress(address);
			}
		}
		logger.info("Suppression d'une firestation " + sVal);
		return sVal;

	}
}
