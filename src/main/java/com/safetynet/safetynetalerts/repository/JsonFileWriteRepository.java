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
	 * @param file2
	 * @return
	 */
	public FileEntryRepository writeFile(FileEntryRepository file2) {

		// Recupération des données dans le fichier json
		ObjectMapper objectMapper = new ObjectMapper();
		String path = prop.getJsonFilePath();

		try {
			objectMapper.writeValue(new File(path), file2);
			logger.debug("Ecriture du fichier Json OK");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Ecriture du fichier Json ECHEC" + e);
		}

		return file2;
	}

}
