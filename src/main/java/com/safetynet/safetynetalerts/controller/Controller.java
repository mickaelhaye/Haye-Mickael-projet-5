package com.safetynet.safetynetalerts.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.safetynet.safetynetalerts.model.FileEntryModel;
import com.safetynet.safetynetalerts.service.JsonFileRecupService;

@RestController
public class Controller {

	/*
	 * @Autowired private JsonFileRecup jsonFileRecup;
	 */

	private JsonFileRecupService jsonFileRecup = new JsonFileRecupService();
	private FileEntryModel file;

	public void start() throws StreamReadException, DatabindException, IOException {

		file = jsonFileRecup.recupFile();
	}

	// Récupérer des personnes couvertes par une station
	@GetMapping(value = "/firestation/{station}")
	public List<Object> afficherUneListePersonne(@PathVariable String station) {
		List<Object> listPersons = file.findByFirestationAListPersons(station);
		return listPersons;
	}

}
