package com.safetynet.safetynetalerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.safetynetalerts.repository.FileEntryRepository;
import com.safetynet.safetynetalerts.repository.JsonFileRecupRepository;
import com.safetynet.safetynetalerts.service.ChildAlertByAddressService;

import jakarta.annotation.PostConstruct;
import lombok.Data;

@RestController
@Data
public class Controller {

	@Autowired
	private JsonFileRecupRepository jsonFileRecup;

	private FileEntryRepository file;

	@PostConstruct
	// Recupération du fichier json
	public void init() {

		file = jsonFileRecup.recupFile();
	}

	// Récupération des personnes couvertes par une station
	@GetMapping(value = "/firestation/{station}")
	public List<Object> afficherUneListePersonne(@PathVariable String station) {
		return file.findByFirestationAListPersons(station);
	}

	// Récupération des enfants en fonction d'une adresse
	@GetMapping(value = "/childAlert/{address}")
	public List<ChildAlertByAddressService> afficherUneListeEnfant(@PathVariable String address) {
		List<ChildAlertByAddressService> listChild = file.findByAddressAListChild(address);
		return listChild;
	}

	// Récupération des numéros de téléphone desservis par la caserne
	@GetMapping(value = "/phoneAlert/{station}")
	public List<String> afficherUneListeNumTelephone(@PathVariable String station) {
		List<String> listPhone = file.findByFirestationAPhone(station);
		return listPhone;
	}

	// Récupération des personnes en fonction d'une adresse
	@GetMapping(value = "/fire/{address}")
	public List<Object> afficherUneListePersonneParAddresse(@PathVariable String address) {
		List<Object> listPerson = file.findByAddressAPerson(address);
		return listPerson;
	}

	// Récupération des personnes en fonction d'une adresse
	@GetMapping(value = "/flood/stations/{station}")
	public List<Object> afficherUneListeFoyerParFirestation(@PathVariable String station) {
		List<Object> listFoyer = file.findByFirestationAFoyer(station);
		return listFoyer;
	}

	// Récupération des personnes en fonction d'un prénom
	@GetMapping(value = "/personInfo/{firstName}")
	public List<String> afficherUneListePersonneParPrenom(@PathVariable String firstName) {
		List<String> listPersonne = file.findByFirstNameAPerson(firstName);
		return listPersonne;
	}

	// Récupération des adresses mail en fonction d'une ville
	@GetMapping(value = "/communityEmail/{city}")
	public List<String> afficherEmailParCity(@PathVariable String city) {
		List<String> listEmail = file.findByCityAEmail(city);
		return listEmail;
	}

}
