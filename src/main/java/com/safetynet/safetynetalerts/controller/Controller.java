package com.safetynet.safetynetalerts.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.safetynetalerts.repository.FileEntryRepository;
import com.safetynet.safetynetalerts.repository.JsonFileReadRepository;

import jakarta.annotation.PostConstruct;
import lombok.Data;

/**
 * Cette classe permet de récupérer le fichier .Json
 * 
 * @author Mickael Hayé
 */

@RestController
@Data
public class Controller {

	private static Logger logger = LoggerFactory.getLogger(FirestationController.class);

	@Autowired
	private JsonFileReadRepository jsonFileRecup;

	private FileEntryRepository file;

	/**
	 * Cette méthode permet de récupérer le fichier json au lancement de
	 * l'application avec l'annotation PostConstruct
	 */
	@PostConstruct
	// Recupération du fichier json
	public void init() {
		logger.debug("récupération diu fichier .json");
		file = jsonFileRecup.recupFile();
	}

}
