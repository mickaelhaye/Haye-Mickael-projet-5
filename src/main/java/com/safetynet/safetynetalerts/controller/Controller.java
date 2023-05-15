package com.safetynet.safetynetalerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.safetynet.safetynetalerts.repository.FileEntryModelRepository;
import com.safetynet.safetynetalerts.repository.JsonFileRecupRepository;

import jakarta.annotation.PostConstruct;

@RestController
public class Controller {

	@Autowired
	private JsonFileRecupRepository jsonFileRecup;

	private FileEntryModelRepository file;

	@PostConstruct
	// Recupération du fichier json
	public void init() {

		file = jsonFileRecup.recupFile();
	}

	// Récupérer des personnes couvertes par une station
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

}
