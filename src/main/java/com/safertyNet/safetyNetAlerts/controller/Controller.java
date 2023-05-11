package com.safertyNet.safetyNetAlerts.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.safertyNet.safetyNetAlerts.model.FileEntry;

import service.JsonFileRecup;

@RestController
public class Controller {

	/*
	 * @Autowired private JsonFileRecup jsonFileRecup;
	 */

	private JsonFileRecup jsonFileRecup = new JsonFileRecup();
	private FileEntry file;

	public void start() throws StreamReadException, DatabindException, IOException {

		file = jsonFileRecup.recupFile();
	}

	// Récupérer des personnes couvertes par une station
	@GetMapping(value = "/firestation/{station}")
	public MappingJacksonValue afficherUneListePersonne(@PathVariable String station) {
		List<Object> listPersons = file.findByFirestationAListPersons(station);

		SimpleBeanPropertyFilter monFiltre = SimpleBeanPropertyFilter.serializeAllExcept("zip");
		FilterProvider listDeNosFiltres = new SimpleFilterProvider().addFilter("monFiltreDynamique", monFiltre);
		MappingJacksonValue produitsFiltres = new MappingJacksonValue(listPersons);
		produitsFiltres.setFilters(listDeNosFiltres);
		return produitsFiltres;
	}

}
