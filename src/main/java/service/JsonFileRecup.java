package service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safertyNet.safetyNetAlerts.model.FileEntry;

@Component
public class JsonFileRecup {

	private FileEntry file = new FileEntry();

	public void recupFile() throws StreamReadException, DatabindException, IOException {

		// Recupération des données dans le fichier json
		ObjectMapper objectMapper = new ObjectMapper();
		String path = "src/main/resources/data/data.json";
		Map<String, Object> map = objectMapper.readValue(new File(path), new TypeReference<Map<String, Object>>() {
		});
		System.out.println(map);

		for (String entry : map.keySet()) {
			Object objectMap = map.get(entry);
			ArrayList<Map<String, Object>> liste = new ArrayList<Map<String, Object>>();
			liste = (ArrayList<Map<String, Object>>) objectMap;

			switch (entry) {
			case "persons":
				recupPersons(liste);
				break;
			case "firestations":
				recupFirestations(liste);
				break;
			case "medicalrecords":
				recupMedicalrecords(liste);
				break;

			default:
				System.out.println("bad value in the json file : " + entry);
			}
		}
	}

	public void recupPersons(ArrayList<Map<String, Object>> liste) {
		for (int i = 0; i < liste.size(); i++) {
			String firstName = "";
			String lastName = "";
			String address = "";
			String city = "";
			String zip = "";
			String phone = "";
			String email = "";
			Map<String, Object> mapListe = liste.get(i);
			for (String entry2 : mapListe.keySet()) {
				switch (entry2) {
				case "firstName":
					firstName = (String) mapListe.get(entry2);
					break;
				case "lastName":
					lastName = (String) mapListe.get(entry2);
					break;
				case "address":
					address = (String) mapListe.get(entry2);
					break;
				case "city":
					city = (String) mapListe.get(entry2);
					break;
				case "zip":
					zip = (String) mapListe.get(entry2);
					break;
				case "phone":
					phone = (String) mapListe.get(entry2);
					break;
				case "email":
					email = (String) mapListe.get(entry2);
					break;

				default:
					System.out.println("bad value in the json file : " + entry2);
				}
			}
			file.addPerson(firstName, lastName, address, city, zip, phone, email);
		}
	}

	public void recupFirestations(ArrayList<Map<String, Object>> liste) {
		for (int i = 0; i < liste.size(); i++) {
			String address = "";
			String station = "";
			Map<String, Object> mapListe = liste.get(i);
			for (String entry2 : mapListe.keySet()) {
				switch (entry2) {
				case "address":
					address = (String) mapListe.get(entry2);
					break;
				case "station":
					station = (String) mapListe.get(entry2);
					break;
				default:
					System.out.println("bad value in the json file : " + entry2);
				}
			}
			file.addFirestation(address, station);
		}
	}

	public void recupMedicalrecords(ArrayList<Map<String, Object>> liste) {
		for (int i = 0; i < liste.size(); i++) {
			String firstName = "";
			String lastName = "";
			String birthdate = "";
			ArrayList<String> medications = new ArrayList<String>();
			ArrayList<String> allergies = new ArrayList<String>();
			Map<String, Object> mapListe = liste.get(i);

			for (String entry2 : mapListe.keySet()) {
				switch (entry2) {
				case "firstName":
					firstName = (String) mapListe.get(entry2);
					break;
				case "lastName":
					lastName = (String) mapListe.get(entry2);
					break;
				case "birthdate":
					birthdate = (String) mapListe.get(entry2);
					break;
				case "medications":
					medications = (ArrayList<String>) mapListe.get(entry2);
					break;
				case "allergies":
					allergies = (ArrayList<String>) mapListe.get(entry2);
					break;
				default:
					System.out.println("bad value in the json file : " + entry2);
				}
			}
			file.addMedicalrecord(firstName, lastName, birthdate, medications, allergies);

		}
	}

}