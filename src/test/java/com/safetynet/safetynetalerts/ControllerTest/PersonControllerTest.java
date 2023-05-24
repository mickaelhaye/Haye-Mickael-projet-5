package com.safetynet.safetynetalerts.ControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void getPersons() throws Exception {
		mockMvc.perform(get("/person")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	void postPersons() throws Exception {
		mockMvc.perform(post("/person").contentType(MediaType.APPLICATION_JSON_VALUE).content(
				"{ \"firstName\":\"John\", \"lastName\":\"Boyd\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\" }")
				.accept(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk());
	}

	@Test
	void patchPersons() throws Exception {
		mockMvc.perform(patch("/person").contentType(MediaType.APPLICATION_JSON_VALUE).content(
				"{ \"firstName\":\"John\", \"lastName\":\"Boyd\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\" }")
				.accept(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk());
	}

	@Test
	void deletePersons() throws Exception {
		mockMvc.perform(delete("/person/JohnBoyd").accept(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	void getAfficherUneListePersonne() throws Exception {
		mockMvc.perform(get("/firestation/3")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	void getafficherUneListeEnfant() throws Exception {
		mockMvc.perform(get("/childAlert/1509 Culver St")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	void afficherUneListeNumTelephone() throws Exception {
		mockMvc.perform(get("/phoneAlert/3")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	void afficherUneListePersonneParAddresse() throws Exception {
		mockMvc.perform(get("/fire/1509 Culver St")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	void afficherUneListeFoyerParFirestation() throws Exception {
		mockMvc.perform(get("/flood/stations/3")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	void afficherUneListePersonneParPrenom() throws Exception {
		mockMvc.perform(get("/personInfo/John")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	void afficherEmailParCity() throws Exception {
		mockMvc.perform(get("/communityEmail/Culver")).andDo(print()).andExpect(status().isOk());
	}

}
