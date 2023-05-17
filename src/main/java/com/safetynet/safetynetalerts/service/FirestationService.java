package com.safetynet.safetynetalerts.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.safetynet.safetynetalerts.model.FirestationModel;

import lombok.Data;

@Data
@Service
public class FirestationService {
	List<FirestationModel> firestations;

	// @PostMapping("/firestation")
	public String addFirestation(FirestationModel firestation) {
		firestations.add(firestation);
		return "la firestation " + firestation.getAddress() + " Station:" + firestation.getStation() + " a été ajoutée";
	}

	// @PatchMapping("/firestation")
	public String updateFirestation(FirestationModel firestation) {
		boolean firestationModifiee = false;
		for (FirestationModel firestationTest : firestations) {
			if (firestationTest.getAddress().equals(firestation.getAddress())) {
				firestationTest.setStation(firestation.getStation());
				firestationModifiee = true;
				break;
			}
		}
		if (!firestationModifiee) {
			return "la firestation " + firestation.getAddress() + " n'est pas référencé";
		}
		return "la firestation " + firestation.getAddress() + " a été modifiée";
	}

	// @DeleteMapping("/firestation/{stationOrAddress}")
	public String deleteFirestation(String stationOrAddress) {
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
			return stationOrAddress + " n'est pas référencé";
		}
		if (firestationSupprimeebyAddress) {
			return "la firestation " + stationOrAddress + " a été supprimée";
		}
		return "les firestations " + stationOrAddress + " ont été supprimées";
	}

}
