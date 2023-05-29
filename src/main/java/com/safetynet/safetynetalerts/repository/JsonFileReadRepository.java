package com.safetynet.safetynetalerts.repository;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.controller.FirestationController;

/**
 * Classe qui permet de récupérer les données dans le fichier d'entrée et de les
 * mettre en forme dans des listes
 * 
 * @author Mickael Hayé
 *
 */
@Repository
public class JsonFileReadRepository {

	@Autowired
	PersonModelRepository personModelRepository;

	private static Logger logger = LoggerFactory.getLogger(FirestationController.class);

	/**
	 * 
	 * @return une classe avec des listes contenant les données du fichier
	 */
	public FileEntryRepository recupFile(String path) throws Exception {

		FileEntryRepository file = null;
		// Recupération des données dans le fichier json
		ObjectMapper objectMapper = new ObjectMapper();

		try {

			file = objectMapper.readValue(new File(path), FileEntryRepository.class);
			logger.debug("Lecture du fichier Json OK");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Lecture du fichier Json ECHEC" + e);
		}

		personModelRepository.saveAll(file.persons);

		return file;
	}

}
