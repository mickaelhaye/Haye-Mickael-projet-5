package com.safetynet.safetynetalerts.repository;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.CustomProperties;
import com.safetynet.safetynetalerts.controller.FirestationController;

/**
 * Classe qui permet de réécrire le fichier Json
 * 
 * @author Mickael Hayé
 *
 */
@Repository
public class JsonFileWriteRepository {

	private static Logger logger = LoggerFactory.getLogger(FirestationController.class);

	@Autowired
	private CustomProperties prop;

	/**
	 * 
	 * @param file
	 */
	public void writeFile(FileEntryRepository file) {

		// Recupération des données dans le fichier json
		ObjectMapper objectMapper = new ObjectMapper();
		// String path = prop.getJsonFilePath(); à remettre
		String path = "src/main/resources/datatest/dataTestWrite.json";

		try {
			objectMapper.writeValue(new File(path), file);
			logger.debug("Ecriture du fichier Json OK");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();//à valider
			logger.error("Ecriture du fichier Json ECHEC" + e);
		}
	}

}
