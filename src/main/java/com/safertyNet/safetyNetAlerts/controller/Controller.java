package com.safertyNet.safetyNetAlerts.controller;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import service.JsonFileRecup;

@Component
public class Controller {

	/*
	 * @Autowired private JsonFileRecup jsonFileRecup;
	 */
	private JsonFileRecup jsonFileRecup = new JsonFileRecup();

	public void start() throws StreamReadException, DatabindException, IOException {

		jsonFileRecup.recupFile();
	}

}
