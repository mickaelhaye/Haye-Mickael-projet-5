package com.safetynet.safetynetalerts.service.impl;

import com.safetynet.safetynetalerts.model.FileEntryModel;

public interface JsonFileReadServiceImpl {

	public FileEntryModel recupFile() throws Exception;

	public FileEntryModel getFile();

}
