package com.safetynet.safetynetalerts.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.safetynetalerts.service.CalculAgeService;

@SpringBootTest
class CalculAgeServiceTest {

	@Autowired
	private CalculAgeService calculAgeService;

	/**
	 * Cas où le calcul de l'age est correct
	 */
	@Test
	void calculAgeTest() {
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
		assertEquals(18, calculAgeService.calculAge(dateActuelleMoins18ans));
	}

	/**
	 * Cas où le calcul e l'age renvoit -999 car une date est érronée
	 */

	@Test
	void calculAgeValeurDateErroneetest() {
		assertEquals(-999, calculAgeService.calculAge("hjiugiugu"));
	}

}
