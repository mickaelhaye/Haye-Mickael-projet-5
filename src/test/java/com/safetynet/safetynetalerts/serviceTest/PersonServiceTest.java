package com.safetynet.safetynetalerts.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
	void findByFirestationAListPersons() {

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
	void findByAddressAListChild() {

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
	void findByFirestationAPhone() {

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
	void findByAddressAPerson() {

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
	void findByFirestationAFoyer() {

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
	void findByFirstNameAPerson() {

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
	void findByCityAEmail() {

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
	void addPerson() {

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
	void updatePerson() {

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
	void updatePersonBadPerson() {

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
	void deletePerson() {
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
	void deletePersonBadPerson() {
		List<PersonModel> persons = personService.getPersons();
		if (persons != null) {
			int nbrPersonneOld = persons.size();
			personService.deletePerson("patrickBoyd");
			persons = personService.getPersons();
			int nbrPersonneNew = persons.size();
			assertEquals(nbrPersonneOld, nbrPersonneNew);
		}
	}
}
