package com.safetynet.safetynetalerts.service.impl;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.configuration.CustomProperties;
import com.safetynet.safetynetalerts.controller.FirestationController;
import com.safetynet.safetynetalerts.model.FileEntryModel;
import com.safetynet.safetynetalerts.service.JsonFileReadService;

import jakarta.annotation.PostConstruct;

/**
 * Classe qui permet de récupérer les données dans le fichier d'entrée et de les
 * mettre en forme dans des listes
 * 
 * @author Mickael Hayé
 *
 */
@Service
public class JsonFileReadServiceImpl implements JsonFileReadService {

	@Autowired
	private CustomProperties prop;

	private FileEntryModel file;

	private static Logger logger = LoggerFactory.getLogger(FirestationController.class);

	/**
	 * 
	 * @return une classe avec des listes contenant les données du fichier
	 */
	@PostConstruct
	@Override
	public FileEntryModel recupFile() throws Exception {

		// Recupération des données dans le fichier json
		ObjectMapper objectMapper = new ObjectMapper();

		file = null;

		try {

			file = objectMapper.readValue(new File(prop.getJsonFilePath()), FileEntryModel.class);
			logger.debug("Lecture du fichier Json OK");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Lecture du fichier Json ECHEC" + e);
		}

		return file;
	}

	@Override
	public FileEntryModel getFile() {
		return file;
	}

}
