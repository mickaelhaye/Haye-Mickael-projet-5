package com.safertyNet.safetyNetAlerts.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;

import com.safetynet.safetynetalerts.service.CalculAgeService;

class CalculAgeSericeTest {

	@Test
	void testAge() {
		// récupération de la date actuelle
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		String formatDate = "dd/MM/yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(formatDate);
		String dateActuelle = sdf.format(calendar.getTime());
		// date actuelle -18ans
		int length = dateActuelle.length();
		String recupAnnee = dateActuelle.substring(length - 4, length);
		int anneeModifiee = Integer.parseInt(recupAnnee) - 18;
		String dateActuelleSansAnnee = dateActuelle.substring(0, length - 4);
		String dateActuelleMoins18ans = dateActuelleSansAnnee + anneeModifiee;

		// pas réussi à mocker
		// when(sdf.format(any(Date.class))).thenReturn("30/02/2020");
		// autowired ne fonctionne pas
		CalculAgeService calculAgeService = new CalculAgeService();
		assertEquals(18, calculAgeService.calculAge(dateActuelleMoins18ans));
	}

}
