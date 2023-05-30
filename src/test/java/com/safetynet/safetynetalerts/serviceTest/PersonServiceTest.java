package com.safetynet.safetynetalerts.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.safetynetalerts.CustomProperties;
import com.safetynet.safetynetalerts.model.PersonModel;
import com.safetynet.safetynetalerts.repository.FileEntryRepository;
import com.safetynet.safetynetalerts.repository.JsonFileReadRepository;
import com.safetynet.safetynetalerts.service.ChildAlertByAddressService;
import com.safetynet.safetynetalerts.service.FoyerbyFirestationService;
import com.safetynet.safetynetalerts.service.PersonService;

@SpringBootTest
class PersonServiceTest {

	@Autowired
	private CustomProperties prop;

	@Autowired
	private JsonFileReadRepository JsonFileReadRepository;

	private static PersonService personService = new PersonService();

	@BeforeEach
	void setUpPerTest() {
		// chargement d'un fichierJson

		FileEntryRepository file;
		try {
			file = JsonFileReadRepository.recupFile(prop.getJsonFileTestPath());
			personService.setPersons(file.getPersons());
			personService.setMedicalrecords(file.getMedicalrecords());
			personService.setFirestations(file.getFirestations());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void findByFirestationAListPersonsTest() {

		List<Object> listObjet;
		try {
			listObjet = personService.findByFirestationAListPersons("2");
			String lastObjet = (String) listObjet.get(listObjet.size() - 1);
			assertEquals("le nombre d'enfants est de 1", lastObjet);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void findByAddressAListChildTest() {

		List<ChildAlertByAddressService> listObjet;
		try {
			listObjet = personService.findByAddressAListChild("1509 Culver St");
			ChildAlertByAddressService child = listObjet.get(0);
			assertEquals(9, child.getAge());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void findByFirestationAPhoneTest() {

		List<String> listPhone;
		try {
			listPhone = personService.findByFirestationAPhone("2");
			String phone = listPhone.get(0);
			assertEquals("841-874-6513", phone);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void findByAddressAPersonTest() {

		List<Object> listObjet;
		try {
			listObjet = personService.findByAddressAPerson("1509 Culver St");
			String lastObjet = (String) listObjet.get(listObjet.size() - 1);
			assertEquals("L'adresse 1509 Culver St : est desservie par la station : 3", lastObjet);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void findByFirestationAFoyerTest() {

		List<Object> listObjet;
		try {
			listObjet = personService.findByFirestationAFoyer("2");
			FoyerbyFirestationService foyerbyFirestationService = (FoyerbyFirestationService) listObjet.get(0);
			assertEquals("29 15th St", foyerbyFirestationService.getAddress());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void findByFirstNameAPersonTest() {

		List<String> listPerson;
		try {
			listPerson = personService.findByFirstNameAPerson("John");
			String person = listPerson.get(0);
			assertEquals(
					"Boyd , 1509 Culver St , 38 , jaboyd@email.com , [aznol:350mg, hydrapermazol:100mg] , [nillacilan]",
					person);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void findByCityAEmailTest() {

		List<String> listEmail;
		try {
			listEmail = personService.findByCityAEmail("Culver");
			String email = listEmail.get(0);
			assertEquals("jaboyd@email.com", email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void addPersonTest() {

		List<PersonModel> persons = personService.getPersons();
		if (persons != null) {
			PersonModel person = new PersonModel("Jean", "Gabin", "30 rue de la victoire", "Nantes", "49860",
					"065989875", "moi@toto.fr");
			personService.addPerson(person);
			person = persons.get(persons.size() - 1);
			assertEquals("Gabin", person.getLastName());
		}
	}

	@Test
	void addPersonBadPersonTest() {

		List<PersonModel> persons = personService.getPersons();
		if (persons != null) {
			int nbrPersonOld = persons.size();
			PersonModel person = new PersonModel("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512",
					"jaboyd@email.com");
			personService.addPerson(person);
			int nbrPersonNew = persons.size();
			assertEquals(nbrPersonOld, nbrPersonNew);
		}
	}

	@Test
	void updatePersonTest() {

		List<PersonModel> persons = personService.getPersons();
		if (persons != null) {
			PersonModel person = persons.get(0);
			String oldCity = person.getCity();
			person = new PersonModel("John", "Boyd", "1509 Culver St", "Rome", "97451", "841-874-6512",
					"jaboyd@email.com");
			personService.updatePerson(person);
			persons = personService.getPersons();
			person = persons.get(0);
			String newCity = person.getCity();
			assertNotEquals(oldCity, newCity);
		}
	}

	@Test
	void updatePersonBadPersonTest() {

		List<PersonModel> persons = personService.getPersons();
		if (persons != null) {
			PersonModel person = persons.get(0);
			String oldCity = person.getCity();
			person = new PersonModel("patrick", "Boyd", "1509 Culver St", "Rome", "97451", "841-874-6512",
					"jaboyd@email.com");
			personService.updatePerson(person);
			persons = personService.getPersons();
			person = persons.get(0);
			String newCity = person.getCity();
			assertEquals(oldCity, newCity);
		}
	}

	@Test
	void deletePersonTest() {
		List<PersonModel> persons = personService.getPersons();
		if (persons != null) {
			int nbrPersonneOld = persons.size();
			personService.deletePerson("JohnBoyd");
			persons = personService.getPersons();
			int nbrPersonneNew = persons.size();
			assertEquals(nbrPersonneOld - 1, nbrPersonneNew);
		}
	}

	@Test
	void deletePersonBadPersonTest() {
		List<PersonModel> persons = personService.getPersons();
		if (persons != null) {
			int nbrPersonneOld = persons.size();
			personService.deletePerson("patrickBoyd");
			persons = personService.getPersons();
			int nbrPersonneNew = persons.size();
			assertEquals(nbrPersonneOld, nbrPersonneNew);
		}
	}

	@Test
	void equalsTest() {
		PersonService personService2 = new PersonService();
		PersonService personService3 = new PersonService();
		assertTrue(personService2.equals(personService3));
	}

	@Test
	void equalsSameObjetTest() {
		assertTrue(personService.equals(personService));
	}

	@Test
	void equalsNullObjetTest() {
		PersonService personServiceNull = null;
		assertFalse(personService.equals(personServiceNull));
	}

	@Test
	void equalsDifferentObjetTest() {
		Object objetDifferent = new Object();
		assertFalse(personService.equals(objetDifferent));
	}

}
