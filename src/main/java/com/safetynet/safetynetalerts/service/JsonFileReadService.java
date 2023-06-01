package com.safetynet.safetynetalerts.service;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.CustomProperties;
import com.safetynet.safetynetalerts.controller.FirestationController;

import jakarta.annotation.PostConstruct;

/**
 * Classe qui permet de récupérer les données dans le fichier d'entrée et de les
 * mettre en forme dans des listes
 * 
 * @author Mickael Hayé
 *
 */
@Service
public class JsonFileReadService {

	@Autowired
	private CustomProperties prop;

	private FileEntryService file;

	private static Logger logger = LoggerFactory.getLogger(FirestationController.class);

	/**
	 * 
	 * @return une classe avec des listes contenant les données du fichier
	 */
	@PostConstruct
	public FileEntryService recupFile() throws Exception {

		// Recupération des données dans le fichier json
		ObjectMapper objectMapper = new ObjectMapper();

		file = null;

		try {

			file = objectMapper.readValue(new File(prop.getJsonFilePath()), FileEntryService.class);
			logger.debug("Lecture du fichier Json OK");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Lecture du fichier Json ECHEC" + e);
		}

		return file;
	}

	public FileEntryService getFile() {
		return file;
	}

}
