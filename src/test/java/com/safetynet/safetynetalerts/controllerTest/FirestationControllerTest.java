package com.safetynet.safetynetalerts.controllerTest;

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
class FirestationControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void getFirestations() throws Exception {
		mockMvc.perform(get("/firestation")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	void postFirestations() throws Exception {
		mockMvc.perform(post("/firestation").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content("{ \"address\":\"29 15th St\", \"station\":\"2\" }").accept(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	void patchFirestations() throws Exception {
		mockMvc.perform(patch("/firestation").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content("{ \"address\":\"90 15th St\", \"station\":\"500\" }")
				.accept(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk());
	}

	@Test
	void deleteFirestations() throws Exception {
		mockMvc.perform(delete("/firestation/3").accept(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
				.andExpect(status().isOk());
	}

}
