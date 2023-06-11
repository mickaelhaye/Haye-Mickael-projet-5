package com.safetynet.safetynetalerts.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.safetynetalerts.model.ChildAlertByAddressModel;
import com.safetynet.safetynetalerts.model.FileEntryModel;
import com.safetynet.safetynetalerts.model.PersonModel;
import com.safetynet.safetynetalerts.service.JsonFileReadService;
import com.safetynet.safetynetalerts.service.PersonService;

/**
 * Cette classe gère les API au niveau de Person
 * 
 * @author Mickael Hayé
 */
@RestController
public class PersonController {

	private static Logger logger = LoggerFactory.getLogger(FirestationController.class);

	@Autowired
	private JsonFileReadService jsonFileReadService;

	@Autowired
	private PersonService personService;

	private FileEntryModel file;

	/**
	 * API pour récupérer la liste des persons
	 * 
	 * @return la liste des persons
	 */
	@GetMapping("/person")
	public List<PersonModel> afficherListePersonne() {
		file = jsonFileReadService.getFile();
		logger.info("Récupération de la liste des persons");
		return file.getPersons();
	}

	/**
	 * API pour récupérer des enfants en fonction d'une adresse
	 * 
	 * @param address (address d'entrée)
	 * @return une liste d'enfants
	 * @throws Exception mauvais retour
	 *                   personService.findByAddressAListChild(address)
	 */
	@GetMapping("/childAlert")
	public List<ChildAlertByAddressModel> afficherUneListeEnfant(@RequestParam(name = "address") String address)
			throws Exception {
		List<ChildAlertByAddressModel> list = personService.findByAddressAListChild(address);
		logger.info("Liste des enfants en fonction d'une adresse " + list);
		return list;
	}

	/**
	 * Pour récupérer des personnes en fonction d'une adresse
	 * 
	 * @param address (adress d'entrée)
	 * @return une liste d'objets (Liste de persons + numéro de station)
	 * @throws Exception mauvais retour personService.findByAddressAPerson(address)
	 */
	@GetMapping("/fire")
	public List<Object> afficherUneListePersonneParAddresse(@RequestParam(name = "address") String address)
			throws Exception {
		List<Object> list = personService.findByAddressAPerson(address);
		logger.info("Liste des personnes en fonction d'une adresse " + list);
		return list;
	}

	/**
	 * Pour récupérer des personnes en fonction d'un prénom
	 * 
	 * @param firstName (prénom d'entrée)
	 * @return une liste de persons
	 * @throws Exception mauvais retour
	 *                   personService.findByFirstNameAPerson(firstName)
	 */
	@GetMapping("/personInfo")
	public List<String> afficherUneListePersonneParPrenom(@RequestParam(name = "firstName") String firstName,
			@RequestParam(name = "lastName") String lastName) throws Exception {
		List<String> list = personService.findByFirstNameAPerson(firstName, lastName);
		logger.info("liste des personnes en fonction d'un prénom " + list);
		return list;
	}

	/**
	 * Pour récupérer des adresses mail en fonction d'une ville
	 * 
	 * @param city (city d'entrée)
	 * @return une liste d'emails
	 * @throws Exception mauvais retour personService.findByCityAEmail(city)
	 */
	// Récupération des adresses mail en fonction d'une ville
	@GetMapping("/communityEmail")
	public List<String> afficherEmailParCity(@RequestParam(name = "city") String city) throws Exception {
		List<String> list = personService.findByCityAEmail(city);
		logger.info("Liste des adresses mail en fonction d'une ville " + list);
		return list;
	}

	/**
	 * API pour rajouter une person
	 * 
	 * @param person
	 * @throws Exception écriture fichier érroné
	 */
	@PostMapping("/person")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String ajouterPerson(@RequestBody PersonModel person) throws Exception {
		String sVal = personService.addPerson(person);
		logger.info("Rajout d'une person" + sVal);
		return sVal;

	}

	/**
	 * API pour modifier une person
	 * 
	 * @param person
	 * @throws Exception écriture fichier érroné
	 */
	@PatchMapping("/person")
	public String mettreAJourPerson(@RequestBody PersonModel person) throws Exception {
		String sVal = personService.updatePerson(person);
		logger.info("Modification d'une person " + sVal);
		return sVal;
	}

	/**
	 * API pour supprimer une person
	 * 
	 * @param firstNameLastName
	 * @throws Exception écriture fichier érroné
	 */

	@DeleteMapping("/person")
	public String supprimerPerson(@RequestParam(name = "firstName") String firstName,
			@RequestParam(name = "lastName") String lastName) throws Exception {
		String sVal = personService.deletePerson(firstName, lastName);
		logger.info("Suppression d'une person " + sVal);
		return sVal;
	}

}
