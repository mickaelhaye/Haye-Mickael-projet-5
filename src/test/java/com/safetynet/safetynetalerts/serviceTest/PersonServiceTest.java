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

import com.safetynet.safetynetalerts.model.ChildAlertByAddressModel;
import com.safetynet.safetynetalerts.model.PersonModel;
import com.safetynet.safetynetalerts.service.JsonFileReadService;
import com.safetynet.safetynetalerts.service.PersonService;

@SpringBootTest
class PersonServiceTest {

	@Autowired
	private JsonFileReadService jsonFileReadRepository;

	@Autowired
	private PersonService personService;

	@BeforeEach
	void setUpPerTest() {
		// chargement d'un fichierJson

		try {
			jsonFileReadRepository.recupFile();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void findByAddressAListChildTest() {

		List<ChildAlertByAddressModel> listObjet;
		try {
			listObjet = personService.findByAddressAListChild("1509 Culver St");
			ChildAlertByAddressModel child = listObjet.get(0);
			assertEquals(10, child.getAge());
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
	void findByFirstNameAPersonTest() {

		List<String> listPerson;
		try {
			listPerson = personService.findByFirstNameAPerson("John", "Boyd");
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

		try {
			List<PersonModel> persons = jsonFileReadRepository.getFile().getPersons();
			PersonModel person = new PersonModel("Jean", "Gabin", "30 rue de la victoire", "Nantes", "49860",
					"065989875", "moi@toto.fr");
			personService.addPerson(person);
			person = persons.get(persons.size() - 1);
			assertEquals("Gabin", person.getLastName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void addPersonBadPersonTest() {

		try {
			List<PersonModel> persons = jsonFileReadRepository.getFile().getPersons();
			int nbrPersonOld = persons.size();
			PersonModel person = new PersonModel("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512",
					"jaboyd@email.com");
			personService.addPerson(person);
			int nbrPersonNew = persons.size();
			assertEquals(nbrPersonOld, nbrPersonNew);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void updatePersonTest() {

		try {
			List<PersonModel> persons = jsonFileReadRepository.getFile().getPersons();
			PersonModel person = persons.get(0);
			String oldCity = person.getCity();
			person = new PersonModel("John", "Boyd", "1509 Culver St", "Rome", "97451", "841-874-6512",
					"jaboyd@email.com");
			personService.updatePerson(person);
			persons = jsonFileReadRepository.getFile().getPersons();
			person = persons.get(0);
			String newCity = person.getCity();
			assertNotEquals(oldCity, newCity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void updatePersonBadPersonTest() {

		try {
			List<PersonModel> persons = jsonFileReadRepository.getFile().getPersons();
			PersonModel person = persons.get(0);
			String oldCity = person.getCity();
			person = new PersonModel("patrick", "Boyd", "1509 Culver St", "Rome", "97451", "841-874-6512",
					"jaboyd@email.com");
			personService.updatePerson(person);
			persons = jsonFileReadRepository.getFile().getPersons();
			person = persons.get(0);
			String newCity = person.getCity();
			assertEquals(oldCity, newCity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void deletePersonTest() {
		try {
			List<PersonModel> persons = jsonFileReadRepository.getFile().getPersons();
			int nbrPersonneOld = persons.size();
			personService.deletePerson("John", "Boyd");
			persons = jsonFileReadRepository.getFile().getPersons();
			int nbrPersonneNew = persons.size();
			assertEquals(nbrPersonneOld - 1, nbrPersonneNew);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void deletePersonBadPersonTest() {
		try {
			List<PersonModel> persons = jsonFileReadRepository.getFile().getPersons();
			int nbrPersonneOld = persons.size();
			personService.deletePerson("patrick", "Boyd");
			persons = jsonFileReadRepository.getFile().getPersons();
			int nbrPersonneNew = persons.size();
			assertEquals(nbrPersonneOld, nbrPersonneNew);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
