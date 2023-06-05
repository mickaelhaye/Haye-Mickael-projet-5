package com.safetynet.safetynetalerts.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.safetynetalerts.controller.FirestationController;
import com.safetynet.safetynetalerts.model.FirestationModel;

import lombok.Data;

/**
 * Cette classe permet de traiter les API concernant Firestation
 * 
 * @author Mickael Hayé
 *
 */
@Data
@Service
public class FirestationService {
	@Autowired
	private JsonFileReadService jsonFileReadRepository;
	private static Logger logger = LoggerFactory.getLogger(FirestationController.class);

	/**
	 * //@PostMapping("/firestation")
	 * 
	 * @param firestation
	 * @return un String contenant le résultat du rajout d'une Firestation
	 */
	public String addFirestation(FirestationModel firestation) {
		logger.debug("addFirestation " + firestation);
		for (FirestationModel firestationTest : jsonFileReadRepository.getFile().getFirestations()) {
			if (firestationTest.getAddress().equals(firestation.getAddress())) {
				return "la firestation " + firestation.getAddress() + " Station:" + firestation.getStation()
						+ " deja presente";
			}
		}
		jsonFileReadRepository.getFile().getFirestations().add(firestation);
		return "la firestation " + firestation.getAddress() + " Station:" + firestation.getStation() + " a ete ajoutee";
	}

	/**
	 * //@PatchMapping("/firestation")
	 * 
	 * @param firestation
	 * @return un String contenant le résultat de la modification d'une Firestation
	 */

	public String updateFirestation(FirestationModel firestation) {
		logger.debug("updateFirestation " + firestation);
		boolean firestationModifiee = false;
		for (FirestationModel firestationTest : jsonFileReadRepository.getFile().getFirestations()) {
			if (firestationTest.getAddress().equals(firestation.getAddress())) {
				firestationTest.setStation(firestation.getStation());
				firestationModifiee = true;
				break;
			}
		}
		if (!firestationModifiee) {
			return "la firestation " + firestation.getAddress() + " n'est pas reference";
		}
		return "la firestation " + firestation.getAddress() + " a ete modifiee";
	}

	/**
	 * //@DeleteMapping("/firestation/{station}")
	 * 
	 * @param station
	 * @return un String contenant le résultat de la suppression d'une Firestation
	 */
	public String deleteFirestationByStation(String station) {
		logger.debug("deleteFirestationByStation " + station);
		boolean firestationSupprimee = false;
		for (int i = 0; i < jsonFileReadRepository.getFile().getFirestations().size(); i++) {
			FirestationModel firestationTest = jsonFileReadRepository.getFile().getFirestations().get(i);
			if (station.equals(firestationTest.getStation())) {
				jsonFileReadRepository.getFile().getFirestations().remove(firestationTest);
				i--;
				firestationSupprimee = true;
			}
		}
		if (!firestationSupprimee) {
			return "la firestation " + station + " n'est pas reference";
		}
		return "les firestations " + station + " ont ete supprimees";
	}

	/**
	 * //@DeleteMapping("/firestation/{Address}")
	 * 
	 * @param Address
	 * @return un String contenant le résultat de la suppression d'une Firestation
	 */
	public String deleteFirestationByAddress(String address) {
		logger.debug("deleteFirestationByAddress " + address);
		boolean firestationSupprimee = false;
		for (int i = 0; i < jsonFileReadRepository.getFile().getFirestations().size(); i++) {
			FirestationModel firestationTest = jsonFileReadRepository.getFile().getFirestations().get(i);
			if (address.equals(firestationTest.getAddress())) {
				jsonFileReadRepository.getFile().getFirestations().remove(firestationTest);
				firestationSupprimee = true;
				break;
			}
		}
		if (!firestationSupprimee) {
			return "la firestation " + address + " n'est pas reference";
		}
		return "les firestations " + address + " ont ete supprimees";
	}

}
