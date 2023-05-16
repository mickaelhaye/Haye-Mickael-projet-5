package com.safetynet.safetynetalerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.safetynet.safetynetalerts.model.PersonModel;
import com.safetynet.safetynetalerts.repository.FileEntryModelRepository;
import com.safetynet.safetynetalerts.repository.JsonFileRecupRepository;
import com.safetynet.safetynetalerts.repository.JsonFileWriteRepository;
import com.safetynet.safetynetalerts.service.ChildAlertByAddressService;

import jakarta.annotation.PostConstruct;

@RestController
public class Controller {

	@Autowired
	private JsonFileRecupRepository jsonFileRecup;

	@Autowired
	private JsonFileWriteRepository jsonFileWrite;

	private FileEntryModelRepository file;

	@PostConstruct
	// Recupération du fichier json
	public void init() {

		file = jsonFileRecup.recupFile();
	}

	// Récupération des personnes couvertes par une station
	@GetMapping(value = "/firestation/{station}")
	public MappingJacksonValue afficherUneListePersonne(@PathVariable String station) {
		List<Object> listPersons = file.findByFirestationAListPersons(station);

		SimpleBeanPropertyFilter monFiltre = SimpleBeanPropertyFilter.serializeAllExcept("city", "zip", "email");
		FilterProvider listDeNosFiltres = new SimpleFilterProvider().addFilter("personModelFiltre_city_zip_email",
				monFiltre);
		MappingJacksonValue listPersonsFiltre = new MappingJacksonValue(listPersons);
		listPersonsFiltre.setFilters(listDeNosFiltres);

		return listPersonsFiltre;
	}

	// Récupération des enfants en fonction d'une adresse
	@GetMapping(value = "/childAlert/{address}")
	public List<ChildAlertByAddressService> afficherUneListeEnfant(@PathVariable String address) {
		List<ChildAlertByAddressService> listChild = file.findByAddressAListChild(address);
		return listChild;
	}

	@PostMapping("/person")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String ajouterPerson(@RequestBody PersonModel person) {
		return file.addPerson(person);
	}

	@PatchMapping("/person")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String mettreAJourPerson(@RequestBody PersonModel person) {
		return file.updatePerson(person);
	}

	@DeleteMapping("/person/{firstNameLastName}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String mettreAJourPerson(@PathVariable String firstNameLastName) {
		return file.deletePerson(firstNameLastName);
	}

}
