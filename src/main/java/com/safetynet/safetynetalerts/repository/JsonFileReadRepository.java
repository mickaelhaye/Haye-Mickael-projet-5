package com.safetynet.safetynetalerts.repository;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.CustomProperties;
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

	private static Logger logger = LoggerFactory.getLogger(FirestationController.class);

	// @Autowired ??????
	// private CustomProperties prop;

	/**
	 * 
	 * @return une classe avec des listes contenant les données du fichier
	 */
	public FileEntryRepository recupFile() {

		FileEntryRepository file = null;
		// Recupération des données dans le fichier json
		ObjectMapper objectMapper = new ObjectMapper();
		CustomProperties prop = new CustomProperties();
		// String path = prop.getJsonFilePath();
		String path = "src/main/resources/datatest/dataTest.json";// remettre correctement

		try {

			file = objectMapper.readValue(new File(path), FileEntryRepository.class);
			logger.debug("Lecture du fichier Json OK");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("Lecture du fichier Json ECHEC" + e);
			return null;// à valider
		}

		return file;
	}

}
