package com.safetynet.safetynetalerts.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static Logger logger = LoggerFactory.getLogger(FirestationController.class);
	List<FirestationModel> firestations;

	/**
	 * //@PostMapping("/firestation")
	 * 
	 * @param firestation
	 * @return un String contenant le résultat du rajout d'une Firestation
	 */
	public String addFirestation(FirestationModel firestation) {
		logger.debug("addFirestation " + firestation);
		for (FirestationModel firestationTest : firestations) {
			if (firestationTest.getAddress().equals(firestation.getAddress())) {
				return "la firestation " + firestation.getAddress() + " Station:" + firestation.getStation()
						+ " deja presente";
			}
		}
		firestations.add(firestation);
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
		for (FirestationModel firestationTest : firestations) {
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
	 * //@DeleteMapping("/firestation/{stationOrAddress}")
	 * 
	 * @param stationOrAddress
	 * @return un String contenant le résultat de la suppression d'une Firestation
	 */
	public String deleteFirestation(String stationOrAddress) {
		logger.debug("deleteFirestation " + stationOrAddress);
		boolean firestationSupprimeebyStation = false;
		boolean firestationSupprimeebyAddress = false;
		for (int i = 0; i < firestations.size(); i++) {
			FirestationModel firestationTest = firestations.get(i);
			if (stationOrAddress.equals(firestationTest.getAddress())) {
				firestations.remove(firestationTest);
				firestationSupprimeebyAddress = true;
				break;
			}
			if (stationOrAddress.equals(firestationTest.getStation())) {
				firestations.remove(firestationTest);
				firestationSupprimeebyStation = true;
				i--;
			}
		}
		if (!firestationSupprimeebyStation && !firestationSupprimeebyAddress) {
			return "la firestation " + stationOrAddress + " n'est pas reference";
		}
		if (firestationSupprimeebyAddress) {
			return "la firestation " + stationOrAddress + " a ete supprimee";
		}
		return "les firestations " + stationOrAddress + " ont ete supprimees";
	}

}
