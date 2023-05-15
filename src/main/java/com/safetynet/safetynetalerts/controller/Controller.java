package com.safetynet.safetynetalerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.safetynetalerts.model.FileEntryModel;
import com.safetynet.safetynetalerts.repository.JsonFileRecupRepository;

import jakarta.annotation.PostConstruct;

@RestController
public class Controller {

	@Autowired
	private JsonFileRecupRepository jsonFileRecup;

	private FileEntryModel file;

	@PostConstruct
	// Recupération du fichier json
	public void init() {

		file = jsonFileRecup.recupFile();
	}

	// Récupérer des personnes couvertes par une station
	@GetMapping(value = "/firestation/{station}")
	public List<Object> afficherUneListePersonne(@PathVariable String station) {
		List<Object> listPersons = file.findByFirestationAListPersons(station);
		return listPersons;
	}

}
