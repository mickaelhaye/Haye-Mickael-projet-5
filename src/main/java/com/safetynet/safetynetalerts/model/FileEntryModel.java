package com.safetynet.safetynetalerts.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.Data;

@Data
@Service
public class FileEntryModel {

	List<PersonModel> persons = new ArrayList<PersonModel>();
	List<FirestationModel> firestations = new ArrayList<FirestationModel>();
	List<MedicalrecordModel> medicalrecords = new ArrayList<MedicalrecordModel>();

	public void addPerson(String firstName, String lastName, String address, String city, String zip, String phone,
			String email) {
		PersonModel person = new PersonModel(firstName, lastName, address, city, zip, phone, email);
		persons.add(person);
	}

	public void addFirestation(String address, String station) {
		FirestationModel firestation = new FirestationModel(address, station);
		firestations.add(firestation);
	}

	public void addMedicalrecord(String firstName, String lastName, String birthdate, ArrayList<String> medications,
			ArrayList<String> allergies) {
		MedicalrecordModel medicalrecord = new MedicalrecordModel(firstName, lastName, birthdate, medications,
				allergies);
		medicalrecords.add(medicalrecord);
	}

	public List<PersonModel> getListPersons() {
		return persons;
	}

	public List<FirestationModel> getListFirestations() {
		return firestations;
	}

	public List<MedicalrecordModel> getListMedicalrecords() {
		return medicalrecords;
	}

	// 1ere request
	public List<Object> findByFirestationAListPersons(String station) {
		// Liste des personnes dépendant d'un numéro de station
		List<Object> listObjects = new ArrayList<Object>();
		List<PersonModel> listPersons2 = new ArrayList<PersonModel>();
		List<FirestationModel> listFirestations2 = new ArrayList<FirestationModel>();
		List<PersonModel> listPersonsPlus18 = new ArrayList<PersonModel>();
		List<PersonModel> listPersons18EtMoins = new ArrayList<PersonModel>();
		for (FirestationModel firestation : firestations) {
			if (firestation.getStation().equals(station)) {
				listFirestations2.add(firestation);
			}
		}
		for (FirestationModel firestation : listFirestations2) {
			for (PersonModel person : persons) {
				if (firestation.getAddress().equals(person.getAddress())) {
					listPersons2.add(person);
				}
			}
		}
		// Décompte du nombre d'adulte de plus de 18 ans et enfants (individu agé de 18
		// ans ou moins)
		for (PersonModel person : listPersons2) {
			for (MedicalrecordModel medicalrecords : medicalrecords) {
				if ((person.getFirstName().equals(medicalrecords.getFirstName()))
						&& (person.getLastName().equals(medicalrecords.getLastName()))) {
					int age = calculAge(medicalrecords.getBirthdate());
					if (age > 18) {
						listPersonsPlus18.add(person);
					} else {
						listPersons18EtMoins.add(person);
					}
				}
			}
		}
		listObjects.add(listPersons2);
		listObjects.add("");
		listObjects.add("le nombre d'adultes est de " + listPersonsPlus18.size());
		listObjects.add("");
		listObjects.add("le nombre d'enfants est de " + listPersons18EtMoins.size());

		return listObjects;
	}

	public int calculAge(String birthdate) {
		// calcul de l'age
		// récupération date actuelle
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		String formatDate = "dd/MM/yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(formatDate);
		String dateActuelle = sdf.format(calendar.getTime());

		Calendar calStr1 = Calendar.getInstance();
		Calendar calStr2 = Calendar.getInstance();
		Calendar calStr0 = Calendar.getInstance();
		Date date1 = null;
		Date date2 = null;
		int nbMois = 0;
		int nbAnnees = 0;
		long nbJours = 0;

		try {
			date1 = new SimpleDateFormat("dd/MM/yyyy").parse(birthdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			date2 = new SimpleDateFormat("dd/MM/yyyy").parse(dateActuelle);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		calStr1.setTime(date1);
		calStr2.setTime(date2);

		nbMois = 0;
		while (calStr1.before(calStr2)) {
			calStr1.add(GregorianCalendar.MONTH, 1);
			if (calStr1.before(calStr2) || calStr1.equals(calStr2)) {
				nbMois++;
			}
		}
		nbAnnees = (nbMois / 12);
		/*
		 * nbMois = (nbMois - (nbAnnees * DOUZE));
		 * 
		 * calStr0 = Calendar.getInstance(); calStr0.setTime(date1);
		 * calStr0.add(GregorianCalendar.YEAR, nbAnnees);
		 * calStr0.add(GregorianCalendar.MONTH, nbMois); nbJours =
		 * (calStr2.getTimeInMillis() - calStr0.getTimeInMillis()) / 86400000;
		 * 
		 * System.out.print("Nb Annees : "+nbAnnees+"\n");
		 * System.out.print("Nb Mois : "+nbMois+"\n");
		 * System.out.print("Nb Jours : "+nbJours+"\n");
		 */
		return nbAnnees;
	}

}