package com.safetynet.safetynetalerts.service.impl;

import java.util.List;

import com.safetynet.safetynetalerts.model.FirestationModel;

public interface FirestationServiceImpl {
	public List<Object> findByFirestationAListPersons(String station) throws Exception;

	public List<Object> findByFirestationAFoyer(String[] ListStation) throws Exception;

	public List<String> findByFirestationAPhone(String station) throws Exception;

	public String addFirestation(FirestationModel firestation);

	public String updateFirestation(FirestationModel firestation);

	public String deleteFirestationByStation(String station);

	public String deleteFirestationByAddress(String address);
}
