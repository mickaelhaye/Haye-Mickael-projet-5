package com.safetynet.safetynetalerts.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.stereotype.Service;

import lombok.Data;

@Data
@Service
public class CalculAgeService {

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
		// Calendar calStr0 = Calendar.getInstance();
		Date date1 = null;
		Date date2 = null;
		int nbMois = 0;
		int nbAnnees = 0;
		// long nbJours = 0;

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
