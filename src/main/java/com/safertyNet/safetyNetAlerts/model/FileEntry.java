package com.safertyNet.safetyNetAlerts.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonFilter;

import lombok.Data;

@Data
@Service
@JsonFilter("monFiltreDynamique")
public class FileEntry {

	Person person;

	List<Person> listPersons = new ArrayList<Person>();
	List<Firestation> listFirestations = new ArrayList<Firestation>();
	List<Medicalrecord> listMedicalrecords = new ArrayList<Medicalrecord>();

	public void addPerson(String firstName, String lastName, String address, String city, String zip, String phone,
			String email) {
		Person person = new Person(firstName, lastName, address, city, zip, phone, email);
		listPersons.add(person);
	}

	public void addFirestation(String address, String station) {
		Firestation firestation = new Firestation(address, station);
		listFirestations.add(firestation);
	}

	public void addMedicalrecord(String firstName, String lastName, String birthdate, ArrayList<String> medications,
			ArrayList<String> allergies) {
		Medicalrecord medicalrecord = new Medicalrecord(firstName, lastName, birthdate, medications, allergies);
		listMedicalrecords.add(medicalrecord);
	}

	public List<Person> getListPersons() {
		return listPersons;
	}

	public List<Firestation> getListFirestations() {
		return listFirestations;
	}

	public List<Medicalrecord> getListMedicalrecords() {
		return listMedicalrecords;
	}

	// 1ere request
	public List<Object> findByFirestationAListPersons(String station) {
		// Liste des personnes dépendant d'un numéro de station
		List<Object> listObjects = new ArrayList<Object>();
		List<Person> listPersons2 = new ArrayList<Person>();
		List<Firestation> listFirestations2 = new ArrayList<Firestation>();
		List<Person> listPersonsPlus18 = new ArrayList<Person>();
		List<Person> listPersons18EtMoins = new ArrayList<Person>();
		for (Firestation firestation : listFirestations) {
			if (firestation.getStation().equals(station)) {
				listFirestations2.add(firestation);
			}
		}
		for (Firestation firestation : listFirestations2) {
			for (Person person : listPersons) {
				if (firestation.getAddress().equals(person.getAddress())) {
					listPersons2.add(person);
				}
			}
		}
		// Décompte du nombre d'adulte de plus de 18 ans et enfants (individu agé de 18
		// ans ou moins)
		for (Person person : listPersons2) {
			for (Medicalrecord medicalrecords : listMedicalrecords) {
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